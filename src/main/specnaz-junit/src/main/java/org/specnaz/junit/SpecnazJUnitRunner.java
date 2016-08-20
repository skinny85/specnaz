package org.specnaz.junit;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.specnaz.Specnaz;
import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.SpecnazSpecRunner;
import org.specnaz.junit.impl.JUnitNotifier;

import java.util.List;

import static java.lang.String.format;
import static org.junit.runner.Description.createSuiteDescription;
import static org.junit.runner.Description.createTestDescription;

public final class SpecnazJUnitRunner extends Runner {
    private final SpecnazSpecRunner specnazSpecRunner;

    public SpecnazJUnitRunner(Class<?> classs) {
        if (Specnaz.class.isAssignableFrom(classs)) {
            Class<? extends Specnaz> specClass = classs.asSubclass(Specnaz.class);
            specnazSpecRunner = new SpecnazSpecRunner(specClass);
        } else {
            throw new IllegalArgumentException(format(
                    "A Specnaz spec class must implement the Specnaz interface; %s does not",
                    classs.getSimpleName()));
        }
    }

    private Description rootDescription;

    @Override
    public Description getDescription() {
        List<SingleTestCase> testCases = specnazSpecRunner.testCases();

        Description rootDescription = createSuiteDescription(specnazSpecRunner.name());

        for (SingleTestCase testCase : specnazSpecRunner.testCases()) {
            rootDescription.addChild(
                    createTestDescription(rootDescription.getDisplayName(), testCase.description));
        }

        this.rootDescription = rootDescription;

        return rootDescription;
    }

    @Override
    public void run(RunNotifier runNotifier) {
        specnazSpecRunner.run(new JUnitNotifier(runNotifier, rootDescription));
    }
}
