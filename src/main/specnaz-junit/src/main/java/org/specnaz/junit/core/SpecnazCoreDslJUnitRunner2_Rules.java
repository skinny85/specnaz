package org.specnaz.junit.core;

import org.junit.AssumptionViolatedException;
import org.junit.ClassRule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.Statement;
import org.specnaz.core.SpecnazCoreDsl;
import org.specnaz.impl.Executable;
import org.specnaz.impl.ExecutableTestGroup;
import org.specnaz.impl.ExecutionClosure;
import org.specnaz.impl.Notifier;
import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.SpecRunner2_Rules;
import org.specnaz.impl.SpecsRegistryViolation;
import org.specnaz.impl.TestsGroup;
import org.specnaz.impl.TreeNode;
import org.specnaz.junit.impl.JUnitDescUtils;
import org.specnaz.junit.impl.JUnitNotifier;
import org.specnaz.junit.impl.JUnitNotifier2_Rules;
import org.specnaz.junit.impl.StubMethod;
import org.specnaz.junit.impl.TestCase2DescriptionMap;
import org.specnaz.junit.rules.Rule;
import org.specnaz.junit.utils.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;

import static org.junit.runner.Description.createSuiteDescription;

public final class SpecnazCoreDslJUnitRunner2_Rules extends Runner {
    private final SpecRunner2_Rules specRunner;
    private final Class<?> classs;
    private final Object specInstance;

    public SpecnazCoreDslJUnitRunner2_Rules(Class<?> classs) throws IllegalStateException {
        this(classs, Utils.instantiateTestClass(classs, SpecnazCoreDsl.class));
    }

    public SpecnazCoreDslJUnitRunner2_Rules(Class<?> classs, Object specInstance)
            throws IllegalStateException {
        this.classs = classs;
        this.specInstance = specInstance;
        try {
            this.specRunner = new SpecRunner2_Rules(specInstance);
        } catch (SpecsRegistryViolation e) {
            throw new IllegalStateException("SpecnazCoreDsl.specification() was never called in the " +
                    "no-argument constructor of " + getClassName());
        }
    }

    private Description extraDescription, rootDescription;
    private TestCase2DescriptionMap testCases2DescriptionsMap;

    @Override
    public Description getDescription() {
        if (extraDescription != null)
            return extraDescription;

        this.extraDescription = createSuiteDescription(classs);
        this.rootDescription = createSuiteDescription(specRunner.name());
        extraDescription.addChild(rootDescription);

        TreeNode<TestsGroup> testsPlan = specRunner.testsPlan();
        IdentityHashMap<SingleTestCase, Description> testCases2Descriptions = new IdentityHashMap<>();
        TestCase2DescriptionMap.Builder testCase2DescriptionsBuilder = new TestCase2DescriptionMap.Builder();
        parseSubGroupDescriptions(testsPlan, rootDescription, testCase2DescriptionsBuilder);

        this.testCases2DescriptionsMap = testCase2DescriptionsBuilder.build();

        return extraDescription;
    }

    @Override
    public void run(RunNotifier runNotifier) {
        Statement entireClassStmt = new Statement() {
            @Override
            public void evaluate() throws Throwable {
                doRun(runNotifier, new JUnitNotifier2_Rules(runNotifier, testCases2DescriptionsMap));
            }
        };

        Statement embellishedStmt = stmtWithClassRules(entireClassStmt);

        try {
            embellishedStmt.evaluate();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private Statement stmtWithClassRules(Statement entireClassStmt) {
        Field[] fields = classs.getFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ClassRule.class)) {
                if ((field.getModifiers() & Modifier.STATIC) == 0) {
                    // throw
                }
                if (!TestRule.class.isAssignableFrom(field.getType())) {
                    // throw
                }

                TestRule testRule;
                try {
                    testRule = (TestRule) field.get(null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                return testRule.apply(entireClassStmt, extraDescription);
            }
        }
        return entireClassStmt;
    }

    private void doRun(RunNotifier runNotifier, JUnitNotifier2_Rules junitNotifier) {
        Collection<ExecutableTestGroup> executableTestGroups = specRunner.executableTestGroups(
                new JUnitNotifier(runNotifier, rootDescription));
        for (ExecutableTestGroup executableTestGroup : executableTestGroups) {
            runTestGroup(executableTestGroup, junitNotifier);
        }
    }

    private void runTestGroup(ExecutableTestGroup executableTestGroup, JUnitNotifier2_Rules junitNotifier) {
        Notifier notifier = executableTestGroup.notifier;

        Throwable beforeAllsError;
        Executable beforeAllsExecutable = executableTestGroup.beforeAllsClosure();
        if (beforeAllsExecutable == null) {
            beforeAllsError = null;
        } else {
            junitNotifier.setupStarted();
            beforeAllsError = beforeAllsExecutable.execute();
            if (beforeAllsError == null) {
                junitNotifier.setupSucceeded();
            } else {
                junitNotifier.setupFailed(beforeAllsError);
            }
        }

        SingleTestCase lastTestCase = null;
        for (ExecutionClosure individualTestClosure : executableTestGroup.individualTestsClosures(beforeAllsError)) {
            Statement stmt = singleCaseStmtWithInstanceRules(individualTestClosure);

            if (stmt == null) {
                junitNotifier.ignored(individualTestClosure.testCase);
            } else {
                junitNotifier.started(individualTestClosure.testCase);
                try {
                    stmt.evaluate();
                    junitNotifier.passed(individualTestClosure.testCase);
                } catch (AssumptionViolatedException e) {
                    junitNotifier.skipped(individualTestClosure.testCase, e);
                } catch (Throwable throwable) {
                    junitNotifier.failed(individualTestClosure.testCase, throwable);
                }
            }

            lastTestCase = individualTestClosure.testCase;
        }

        Executable afterAllsClosure = executableTestGroup.afterAllsClosure();
        if (afterAllsClosure != null) {
            junitNotifier.teardownStarted(lastTestCase);
            Throwable afterAllsError = afterAllsClosure.execute();
            Throwable effectiveError = beforeAllsError == null ? afterAllsError : beforeAllsError;
            if (effectiveError == null) {
                junitNotifier.teardownSucceeded(lastTestCase);
            } else {
                junitNotifier.teardownFailed(lastTestCase, effectiveError);
            }
        }
    }

    private Statement singleCaseStmtWithInstanceRules(ExecutionClosure individualTestClosure) {
        Statement singleTestCaseStmt = singleTestCaseStmt(individualTestClosure);

        if (singleTestCaseStmt == null)
            return null;

        Field[] fields = classs.getFields();
        for (Field field : fields) {
            if (Rule.class.isAssignableFrom(field.getType())) {
                if ((field.getModifiers() & Modifier.STATIC) != 0) {
                    // throw
                }

                Rule methodRuleHolder;
                try {
                    methodRuleHolder = (Rule) field.get(specInstance);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                Rule.Wrapper wrapper = methodRuleHolder.new Wrapper();
                wrapper.reset();

                return wrapper.apply(singleTestCaseStmt,
                        testCases2DescriptionsMap.findDesc(individualTestClosure.testCase),
                        StubMethod.frameworkMethod(), specInstance);
            }
        }

        return singleTestCaseStmt;
    }

    private Statement singleTestCaseStmt(ExecutionClosure individualTestClosure) {
        return individualTestClosure.ignored
                ? null
                : new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Throwable throwable = individualTestClosure.executable.execute();
                if (throwable != null)
                    throw throwable;
            }
        };
    }

    private void parseSubGroupDescriptions(TreeNode<TestsGroup> testsGroupNode, Description parentDescription,
            TestCase2DescriptionMap.Builder testCase2DescriptionsBuilder) {
        List<SingleTestCase> testCases = testsGroupNode.value.testCases;
        for (SingleTestCase testCase : testCases) {
            Description description = JUnitDescUtils.makeTestDesc(testCase.description, parentDescription);
            parentDescription.addChild(description);
            testCase2DescriptionsBuilder.addDescMapping(testCase, description);
        }
        if (!testCases.isEmpty() && testsGroupNode.value.afterAllsCount() > 0) {
            Description description = JUnitDescUtils.makeTestDesc("teardown", parentDescription);
            parentDescription.addChild(description);
            for (SingleTestCase testCase : testCases) {
                testCase2DescriptionsBuilder.addTeardownMapping(testCase, description);
            }
        }

        for (TreeNode<TestsGroup> child : testsGroupNode.children()) {
            if (child.value.testsInTree > 0) {
                Description suiteDescription = createSuiteDescription(child.value.description);
                parentDescription.addChild(suiteDescription);
                parseSubGroupDescriptions(child, suiteDescription, testCase2DescriptionsBuilder);
            }
        }
    }

    private String getClassName() {
        return classs.getSimpleName();
    }
}
