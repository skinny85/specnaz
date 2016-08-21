package org.specnaz.junit;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.specnaz.Specnaz;
import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.SpecRunner;
import org.specnaz.impl.TestsGroup;
import org.specnaz.impl.TreeNode;
import org.specnaz.junit.impl.JUnitNotifier;

import java.util.List;

import static java.lang.String.format;
import static org.junit.runner.Description.createSuiteDescription;
import static org.specnaz.junit.impl.JUnitDescUtils.addChildDescription;

public final class SpecnazJUnitRunner extends Runner {
    private final SpecRunner specnazSpecRunner;
    private final String className;

    public SpecnazJUnitRunner(Class<?> classs) {
        className = classs.getSimpleName();
        if (Specnaz.class.isAssignableFrom(classs)) {
            Class<? extends Specnaz> specClass = classs.asSubclass(Specnaz.class);
            Specnaz specInstance;
            try {
                specInstance = specClass.newInstance();
            } catch (Exception e) {
                throw new IllegalArgumentException(format(
                        "Could not instantiate test class '%s'", className), e);
            }
            specnazSpecRunner = new SpecRunner(specInstance);
        } else {
            throw new IllegalArgumentException(format(
                    "A Specnaz spec class must implement the Specnaz interface; %s does not",
                    className));
        }
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
        if (!testCases.isEmpty()) {
            for (SingleTestCase testCase : testCases) {
                addChildDescription(testCase.description, parentDescription);
            }
            if (testsGroupNode.value.afterAllsCount() > 0)
                addChildDescription("teardown", parentDescription);
        }

        for (TreeNode<TestsGroup> child : testsGroupNode.children()) {
            if (child.value.testsInTree > 0) {
                Description suiteDescription = createSuiteDescription(child.value.description);
                parentDescription.addChild(suiteDescription);
                parseSubGroupDescriptions(child, suiteDescription);
            }
        }
    }
}
