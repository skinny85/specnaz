package org.specnaz.junit.core;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.specnaz.core.SpecnazCoreDsl;
import org.specnaz.impl.ExecutableTestGroup;
import org.specnaz.impl.ExecutionClosure;
import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.SpecRunner2_Rules;
import org.specnaz.impl.SpecsRegistryViolation;
import org.specnaz.impl.TestsGroup;
import org.specnaz.impl.TreeNode;
import org.specnaz.junit.impl.JUnitNotifier;
import org.specnaz.junit.utils.Utils;

import java.util.Collection;
import java.util.List;

import static org.junit.runner.Description.createSuiteDescription;
import static org.specnaz.junit.impl.JUnitDescUtils.addChildDescription;

public final class SpecnazCoreDslJUnitRunner2_Rules extends Runner {
    private final SpecRunner2_Rules specRunner;
    private final Class<?> classs;

    public SpecnazCoreDslJUnitRunner2_Rules(Class<?> classs) throws IllegalStateException {
        this(classs, Utils.instantiateTestClass(classs, SpecnazCoreDsl.class));
    }

    public SpecnazCoreDslJUnitRunner2_Rules(Class<?> classs, Object specInstance)
            throws IllegalStateException {
        this.classs = classs;
        try {
            this.specRunner = new SpecRunner2_Rules(specInstance);
        } catch (SpecsRegistryViolation e) {
            throw new IllegalStateException("SpecnazCoreDsl.specification() was never called in the " +
                    "no-argument constructor of " + getClassName());
        }
    }

    private Description extraDescription, rootDescription;

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
        parseSubGroupDescriptions(testsPlan, rootDescription);

        this.extraDescription = extraDescription;
        this.rootDescription = rootDescription;

        return extraDescription;
    }

    @Override
    public void run(RunNotifier runNotifier) {
        Collection<ExecutableTestGroup> executableTestGroups = specRunner.executableTestGroups(
                new JUnitNotifier(runNotifier, rootDescription));
        for (ExecutableTestGroup executableTestGroup : executableTestGroups) {
            if (executableTestGroup != null) {
                for (ExecutionClosure individualTestClosure : executableTestGroup.individualTestsClosures(null)) {
                    if (individualTestClosure != null) {
                        try {
                            individualTestClosure.execute();
                        } catch (Throwable e) {
                            // ignore
                        }
                    }
                }
            }
        }
    }

    private void parseSubGroupDescriptions(TreeNode<TestsGroup> testsGroupNode, Description parentDescription) {
        List<SingleTestCase> testCases = testsGroupNode.value.testCases;
        for (SingleTestCase testCase : testCases)
            addChildDescription(testCase.description, parentDescription);
        if (!testCases.isEmpty() && testsGroupNode.value.afterAllsCount() > 0)
            addChildDescription("teardown", parentDescription);

        for (TreeNode<TestsGroup> child : testsGroupNode.children()) {
            if (child.value.testsInTree > 0) {
                Description suiteDescription = createSuiteDescription(child.value.description);
                parentDescription.addChild(suiteDescription);
                parseSubGroupDescriptions(child, suiteDescription);
            }
        }
    }

    private String getClassName() {
        return classs.getSimpleName();
    }
}
