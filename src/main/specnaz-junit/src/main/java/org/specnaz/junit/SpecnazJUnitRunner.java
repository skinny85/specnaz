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
import static org.junit.runner.Description.createTestDescription;

public final class SpecnazJUnitRunner extends Runner {
    private final SpecRunner specnazSpecRunner;

    public SpecnazJUnitRunner(Class<?> classs) {
        if (Specnaz.class.isAssignableFrom(classs)) {
            Class<? extends Specnaz> specClass = classs.asSubclass(Specnaz.class);
            specnazSpecRunner = new SpecRunner(specClass);
        } else {
            throw new IllegalArgumentException(format(
                    "A Specnaz spec class must implement the Specnaz interface; %s does not",
                    classs.getSimpleName()));
        }
    }

    private Description rootDescription;

    @Override
    public Description getDescription() {
        TreeNode<TestsGroup> testsPlan = specnazSpecRunner.testsPlan();

        Description rootDescription = createSuiteDescription(specnazSpecRunner.name());

        parseSubGroupDescriptions(testsPlan, rootDescription);

        this.rootDescription = rootDescription;

        return rootDescription;
    }

    @Override
    public void run(RunNotifier runNotifier) {
        specnazSpecRunner.run(new JUnitNotifier(runNotifier, rootDescription));
    }

    private void parseSubGroupDescriptions(TreeNode<TestsGroup> testsGroupNode, Description parentDescription) {
        for (SingleTestCase testCase : testsGroupNode.value.testCases) {
            parentDescription.addChild(
                    createTestDescription(parentDescription.getDisplayName(), testCase.description));
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
