package org.specnaz.impl;

import org.specnaz.utils.TestClosure;
import org.specnaz.utils.ThrowableExpectations;

public final class SingleExceptionTestCase<T extends Throwable> extends SingleTestCase {
    private final ThrowableExpectations.Wrapper<T> throwableExpectations;
    private final TestClosure testBody;

    public SingleExceptionTestCase(TestCaseType type, Class<T> expectedException,
            String description, TestClosure testBody) {
        this(new ThrowableExpectations.Wrapper<>(new ThrowableExpectations<>(expectedException)),
                description, testBody, type);
    }

    public SingleExceptionTestCase(ThrowableExpectations.Wrapper<T> throwableExpectations,
            String description, TestClosure testBody, TestCaseType type) {
        super(type, description);
        this.throwableExpectations = throwableExpectations;
        this.testBody = testBody;
    }

    @Override
    public Throwable exercise() {
        Throwable resultingException = SingleTestCase.invokeCallback(testBody);
        return SingleTestCase.invokeCallback(() -> {
            throwableExpectations.verify(resultingException);
        });
    }
}
