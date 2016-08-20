package org.specnaz.impl;

import org.specnaz.Specnaz;

import java.util.List;

import static java.lang.String.format;

public class SpecnazSpecRunner {
    private final SpecDescriptor specDescriptor;
    private List<SingleTestCase> testCases;

    public SpecnazSpecRunner(Class<? extends Specnaz> specClass) {
        Specnaz specInstance;
        try {
            specInstance = specClass.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException(format(
                    "Could not instantiate test class '%s'", specClass.getSimpleName()), e);
        }
        this.specDescriptor = SpecsRegistry.specFor(specInstance);
    }

    public String name() {
        return specDescriptor.description;
    }

    // ToDo: rename these
    public List<SingleTestCase> testCases() {
        if (testCases == null)
            testCases = initTestCases();
        return testCases;
    }

    public void run(Notifier notifier) {
        for (SingleTestCase testCase : testCases()) {
            notifier.started(testCase);
            try {
                testCase.testBody.invoke();
                notifier.passed(testCase);
            } catch (Exception | AssertionError e) {
                notifier.failed(e);
            }
        }
    }

    private List<SingleTestCase> initTestCases() {
        ListSpecBuilder listSpecBuilder = new ListSpecBuilder();
        specDescriptor.specClosure.accept(listSpecBuilder);
        return listSpecBuilder.testCases();
    }
}
