package org.specnaz.junit.core;

import org.junit.AssumptionViolatedException;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.Statement;
import org.specnaz.core.SpecnazCoreDsl;
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
import org.specnaz.junit.impl.StubMethod;
import org.specnaz.junit.rules.Rule;
import org.specnaz.junit.utils.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;

import static org.junit.runner.Description.createSuiteDescription;
import static org.specnaz.junit.impl.JUnitDescUtils.addChildDescription;

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
    private IdentityHashMap<SingleTestCase, Description> testCases2Descriptions;

    @Override
    public Description getDescription() {
        if (extraDescription != null)
            return extraDescription;

        /*
         * JUnit sucks, and behaves differently when running a single test
         * and running a group of tests. In the former case, the top-level description
         * is always the name of the class, even if you overwrite it like we do
         * here with the spec name (and it messes up the names of the tests in the root group).
         * To make the behavior consistent, we add an extra
         * description with the class name at the top level ourselves.
         */
        Description extraDescription = createSuiteDescription(getClassName());
        Description rootDescription = createSuiteDescription(specRunner.name());
        extraDescription.addChild(rootDescription);

        TreeNode<TestsGroup> testsPlan = specRunner.testsPlan();
        IdentityHashMap<SingleTestCase, Description> testCases2Descriptions = new IdentityHashMap<>();
        parseSubGroupDescriptions(testsPlan, rootDescription, testCases2Descriptions);

        this.extraDescription = extraDescription;
        this.rootDescription = rootDescription;
        this.testCases2Descriptions = testCases2Descriptions;

        return extraDescription;
    }

    @Override
    public void run(RunNotifier runNotifier) {
        Collection<ExecutableTestGroup> executableTestGroups = specRunner.executableTestGroups(
                new JUnitNotifier(runNotifier, rootDescription));
        for (ExecutableTestGroup executableTestGroup : executableTestGroups) {
            Notifier notifier = executableTestGroup.notifier;

            for (ExecutionClosure individualTestClosure : executableTestGroup.individualTestsClosures(null)) {
                Statement stmt = singleCaseStmtWithInstanceRules(individualTestClosure);

                if (stmt == null) {
                    notifier.ignored(individualTestClosure.testCase);
                } else {
                    notifier.started(individualTestClosure.testCase);
                    try {
                        stmt.evaluate();
                        notifier.passed(individualTestClosure.testCase);
                    } catch (AssumptionViolatedException e) {
                        notifier.skipped(individualTestClosure.testCase, e);
                    } catch (Throwable throwable) {
                        notifier.failed(individualTestClosure.testCase, throwable);
                    }
                }
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
                        testCases2Descriptions.get(individualTestClosure.testCase),
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
            IdentityHashMap<SingleTestCase, Description> testCases2Descriptions) {
        List<SingleTestCase> testCases = testsGroupNode.value.testCases;
        for (SingleTestCase testCase : testCases) {
            Description description = JUnitDescUtils.makeTestDesc(testCase.description, parentDescription);
            parentDescription.addChild(description);
            testCases2Descriptions.put(testCase, description);
        }
        if (!testCases.isEmpty() && testsGroupNode.value.afterAllsCount() > 0) {
            addChildDescription("teardown", parentDescription);
            // ToDo handle afterAll methods correctly in testCases2Descriptions
        }

        for (TreeNode<TestsGroup> child : testsGroupNode.children()) {
            if (child.value.testsInTree > 0) {
                Description suiteDescription = createSuiteDescription(child.value.description);
                parentDescription.addChild(suiteDescription);
                parseSubGroupDescriptions(child, suiteDescription, testCases2Descriptions);
            }
        }
    }

    private String getClassName() {
        return classs.getSimpleName();
    }
}
