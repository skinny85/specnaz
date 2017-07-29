package org.specnaz.junit.core;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.specnaz.Specnaz;
import org.specnaz.core.SpecnazCoreDsl;
import org.specnaz.impl.Example;
import org.specnaz.impl.SpecRunner;
import org.specnaz.impl.SpecsRegistryViolation;
import org.specnaz.impl.TestsGroup;
import org.specnaz.impl.TreeNode;
import org.specnaz.junit.impl.JUnitNotifier;
import org.specnaz.junit.utils.Utils;

import java.util.List;

import static org.junit.runner.Description.createSuiteDescription;
import static org.specnaz.junit.impl.JUnitDescUtils.addChildDescription;

/**
 * The JUnit {@link Runner} for {@link SpecnazCoreDsl}.
 * This is the runner you should use when writing your own custom Specnaz DSL.
 */
public final class SpecnazCoreDslJUnitRunner extends Runner {
    private final SpecRunner specRunner;
    private final String className;

    /**
     * This is JUnit's entry point for {@link Runner}s.
     *
     * @param classs
     *     the class of the tests currently being run.
     *     It must implement and obey the contract of the
     *     {@link SpecnazCoreDsl} interface
     */
    public SpecnazCoreDslJUnitRunner(Class<?> classs) throws IllegalStateException {
        this(classs.getSimpleName(), Utils.instantiateTestClass(classs, SpecnazCoreDsl.class));
    }

    /**
     * This is the extension point for custom {@link Runner}s that want
     * to leverage existing JUnit infrastructure provided by this class.
     * <p>
     * It's used by the Kotlin Specnaz bindings, for example.
     *
     * @param className
     *     the name of the original class that was run as a test class
     * @param specInstance
     *     an instance of the test object.
     *     It had to be registered for running, most likely by calling,
     *     directly or indirectly, a method like {@link Specnaz#describes},
     *     {@link SpecnazCoreDsl#specification}, or its equivalent
     * @throws IllegalStateException
     *     if the {@code specInstance} was never registered for running
     */
    public SpecnazCoreDslJUnitRunner(String className, Object specInstance)
            throws IllegalStateException {
        this.className = className;
        try {
            this.specRunner = new SpecRunner(specInstance);
        } catch (SpecsRegistryViolation e) {
            throw new IllegalStateException("SpecnazCoreDsl.specification() was never called in the " +
                    "no-argument constructor of " + className);
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
        Description extraDescription = createSuiteDescription(className);
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
        specRunner.run(new JUnitNotifier(runNotifier, rootDescription));
    }

    private void parseSubGroupDescriptions(TreeNode<TestsGroup> testsGroupNode, Description parentDescription) {
        List<Example> examples = testsGroupNode.value.testCases;
        for (Example example : examples)
            addChildDescription(example.testCase.description(), parentDescription);
        if (!examples.isEmpty() && testsGroupNode.value.afterAllsCount() > 0)
            addChildDescription("teardown", parentDescription);

        for (TreeNode<TestsGroup> child : testsGroupNode.children()) {
            if (child.value.testsInTree > 0) {
                Description suiteDescription = createSuiteDescription(child.value.description);
                parentDescription.addChild(suiteDescription);
                parseSubGroupDescriptions(child, suiteDescription);
            }
        }
    }
}
