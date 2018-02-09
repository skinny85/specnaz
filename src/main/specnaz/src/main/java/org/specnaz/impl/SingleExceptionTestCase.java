package org.specnaz.impl;

import org.specnaz.TestSettings;
import org.specnaz.utils.TestClosure;
import org.specnaz.utils.ThrowableExpectations;

public final class SingleExceptionTestCase<T extends Throwable> extends SingleTestCase {
    private final ThrowableExpectations.Wrapper<T> throwableExpectations;

    public SingleExceptionTestCase(ThrowableExpectations<T> throwableExpectations,
            String description, TestClosure testBody, TestCaseType type) {
        super(new TestSettings(), description, testBody, type);
        this.throwableExpectations = new ThrowableExpectations.Wrapper<>(throwableExpectations);
    }

    @Override
    public Throwable exercise() {
        Throwable resultingException = super.exercise();
        return SingleTestCase.invokeCallback(() -> {
            throwableExpectations.verify(resultingException);
        });
    }
}
