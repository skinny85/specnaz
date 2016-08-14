package org.specnaz.java;

import org.specnaz.SpecnazSuiteBuilder;

import static java.util.Objects.requireNonNull;

public final class SpecnazJavaSuiteBuilder {
    private final SpecnazSuiteBuilder specnazSuiteBuilder;

    public SpecnazJavaSuiteBuilder(SpecnazSuiteBuilder specnazSuiteBuilder) {
        this.specnazSuiteBuilder = requireNonNull(specnazSuiteBuilder);
    }

    public void beforeEach(Runnable setup) {
        specnazSuiteBuilder.beforeEach(it -> {
            setup.run();
            return null;
        });
    }

    public void should(String description, Runnable testBody) {
        specnazSuiteBuilder.should(description, it -> {
            testBody.run();
            return null;
        });
    }

    public void afterEach(Runnable teardown) {
        specnazSuiteBuilder.afterEach(it -> {
            teardown.run();
            return null;
        });
    }
}
