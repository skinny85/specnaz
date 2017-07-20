package org.specnaz.impl;

import org.specnaz.SpecBuilder;
import org.specnaz.core.CoreDslBuilder;
import org.specnaz.utils.TestClosure;

import static java.lang.String.format;

public final class SpecBuilderCoreDslAdapter implements SpecBuilder {
    private final CoreDslBuilder coreDslBuilder;

    public SpecBuilderCoreDslAdapter(CoreDslBuilder coreDslBuilder) {
        this.coreDslBuilder = coreDslBuilder;
    }

    @Override
    public void beginsAll(TestClosure closure) {
        coreDslBuilder.beforeAll(closure);
    }

    @Override
    public void beginsEach(TestClosure closure) {
        coreDslBuilder.beforeEach(closure);
    }

    @Override
    public void should(String description, TestClosure testBody) {
        coreDslBuilder.test("should " + description, testBody);
    }

    @Override
    public void shouldThrow(Class<? extends Throwable> expectedException, String description, TestClosure testBody) {
        coreDslBuilder.testExpectingException(expectedException,
                format("should throw %s %s", expectedException.getSimpleName(), description),
                testBody);
    }

    @Override
    public void endsEach(TestClosure closure) {
        coreDslBuilder.afterEach(closure);
    }

    @Override
    public void endsAll(TestClosure closure) {
        coreDslBuilder.afterAll(closure);
    }

    @Override
    public void describes(String description, Runnable specClosure) {
        coreDslBuilder.subSpecification(description, specClosure);
    }
}
