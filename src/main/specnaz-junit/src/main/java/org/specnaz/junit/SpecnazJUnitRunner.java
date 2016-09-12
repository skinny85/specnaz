package org.specnaz.junit;

import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.specnaz.Specnaz;
import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.SpecRunner;
import org.specnaz.impl.SpecsRegistry;
import org.specnaz.impl.TestsGroup;
import org.specnaz.impl.TreeNode;
import org.specnaz.junit.impl.JUnitNotifier;

import java.util.List;

import static java.lang.String.format;
import static org.junit.runner.Description.createSuiteDescription;
import static org.specnaz.junit.impl.JUnitDescUtils.addChildDescription;

/**
 * The JUnit {@link Runner} used for running {@link Specnaz} specifications.
 * The shortest way to use it is to extend {@link SpecnazJUnit}.
 * If your test class needs to extend a different class,
 * you can always specify this runner with the {@link RunWith} annotation:
 *
 * <pre>
 * <code>
 * {@literal @}RunWith(SpecnazJUnitRunner.class)
 *     public class MySpec extends SomeCustomClass implements Specnaz {{
 *         describes("something", it -&gt; {
 *             // specification body here
 *         });
 *     }}
 * </code>
 * </pre>
 */
public class SpecnazJUnitRunner extends Runner {
    private static Specnaz instantiate(Class<?> classs) {
        String className = classs.getSimpleName();
        if (Specnaz.class.isAssignableFrom(classs)) {
            Class<? extends Specnaz> specClass = classs.asSubclass(Specnaz.class);
            Specnaz specInstance;
            try {
                specInstance = specClass.newInstance();
            } catch (Exception e) {
                throw new IllegalArgumentException(format(
                        "Could not instantiate test class '%s'", className), e);
            }
            return specInstance;
        } else {
            throw new IllegalArgumentException(format(
                    "A Specnaz spec class must implement the Specnaz interface; %s does not",
                    className));
        }
    }

    private final SpecRunner specnazSpecRunner;
    private final String className;

    /**
     * This is JUnit's entry point for {@link Runner}s.
     *
     * @param classs
     *     the class of the tests currently being run.
     *     It must implement and obey the contract of the
     *     {@link Specnaz} interface
     */
    public SpecnazJUnitRunner(Class<?> classs) {
        this(classs.getSimpleName(), instantiate(classs));
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
     *     an instance of the test object that was registered in {@link SpecsRegistry}
     *     (most likely by calling something like {@link Specnaz#describes})
     */
    protected SpecnazJUnitRunner(String className, Object specInstance)
            throws IllegalStateException {
        this.className = className;
        this.specnazSpecRunner = new SpecRunner(specInstance);
    }

    private Description rootDescription;

    @Override
    public Description getDescription() {
        /*
         * JUnit sucks, and behaves differently when running a single test
         * and running a group of tests. In the former case, the top-level description
         * is always the name of the class, even if you overwrite it like we do
         * here with the spec name (and it messes up the names of the tests in the root group).
         * To make the behavior consistent, we add an extra
         * description with the class name at the top level ourselves.
         */
        TreeNode<TestsGroup> testsPlan = specnazSpecRunner.testsPlan();

        Description extraDescription = createSuiteDescription(className);

        Description rootDescription = createSuiteDescription(specnazSpecRunner.name());
        extraDescription.addChild(rootDescription);

        parseSubGroupDescriptions(testsPlan, rootDescription);

        this.rootDescription = rootDescription;

        return extraDescription;
    }

    @Override
    public void run(RunNotifier runNotifier) {
        specnazSpecRunner.run(new JUnitNotifier(runNotifier, rootDescription));
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
}
