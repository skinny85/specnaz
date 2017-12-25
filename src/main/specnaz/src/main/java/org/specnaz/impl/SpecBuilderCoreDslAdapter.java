package org.specnaz.impl;

import org.specnaz.SpecBuilder;
import org.specnaz.core.CoreDslBuilder;
import org.specnaz.utils.TestClosure;
import org.specnaz.utils.ThrowableExpectations;

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
    public <T extends Throwable> ThrowableExpectations<T> shouldThrow(Class<T> expectedException,
            String description, TestClosure testBody) {
        return coreDslBuilder.testExpectingException(expectedException,
                format("should throw %s %s", expectedException.getSimpleName(), description),
                testBody);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void fshould(String description, TestClosure testBody) {
        coreDslBuilder.focusedTest("should " + description, testBody);
    }

    @Override
    @SuppressWarnings("deprecation")
    public <T extends Throwable> ThrowableExpectations<T> fshouldThrow(Class<T> expectedException,
            String description, TestClosure testBody) {
        return coreDslBuilder.focusedTestExpectingException(expectedException,
                format("should throw %s %s", expectedException.getSimpleName(), description),
                testBody);
    }

    @Override
    public void xshould(String description, TestClosure testBody) {
        coreDslBuilder.ignoredTest("should " + description, testBody);
    }

    @Override
    public <T extends Throwable> ThrowableExpectations<T> xshouldThrow(Class<T> expectedException,
            String description, TestClosure testBody) {
        return coreDslBuilder.ignoredTestExpectingException(expectedException,
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

    @Override
    public void fdescribes(String description, Runnable specClosure) {
        coreDslBuilder.focusedSubSpecification(description, specClosure);
    }

    @Override
    public void xdescribes(String description, Runnable specClosure) {
        coreDslBuilder.ignoredSubSpecification(description, specClosure);
    }
}
