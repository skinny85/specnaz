package org.specnaz.impl;

import org.specnaz.utils.TestClosure;
import org.specnaz.utils.ThrowableExpectations;

public final class SingleExceptionTestCase<T extends Throwable> extends SingleTestCase {
    private final ThrowableExpectations.Wrapper<T> throwableExpectations;
    private final TestClosure testBody;

    public SingleExceptionTestCase(TestCaseType type, Class<T> expectedException,
            String description, TestClosure testBody) {
        this(type, new ThrowableExpectations.Wrapper<>(new ThrowableExpectations<>(expectedException)),
                description, testBody);
    }

    private SingleExceptionTestCase(TestCaseType type, ThrowableExpectations.Wrapper<T> throwableExpectations,
            String description, TestClosure testBody) {
        super(type, description);
        this.throwableExpectations = throwableExpectations;
        this.testBody = testBody;
    }

    @Override
    public SingleTestCase type(TestCaseType type) {
        return new SingleExceptionTestCase<>(type, throwableExpectations, description, testBody);
    }

    @Override
    public Throwable exercise() {
        Throwable resultingException = SingleTestCase.invokeCallback(testBody);
        return SingleTestCase.invokeCallback(() -> {
            throwableExpectations.verify(resultingException);
        });
    }

    public ThrowableExpectations<T> throwableExpectations() {
        return throwableExpectations.inner();
    }
}
