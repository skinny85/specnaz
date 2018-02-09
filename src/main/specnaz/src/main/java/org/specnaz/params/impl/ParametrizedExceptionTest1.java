package org.specnaz.params.impl;

import org.specnaz.impl.SingleExceptionTestCase;
import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams1;
import org.specnaz.utils.ThrowableExpectations;

public final class ParametrizedExceptionTest1<T extends Throwable, P> extends AbstractParametrizedTest1<P> {
    private final ThrowableExpectations<T> throwableExpectations;

    public ParametrizedExceptionTest1(ThrowableExpectations<T> throwableExpectations,
            String description, TestClosureParams1<P> testBody, TestCaseType testCaseType) {
        super(description, testBody, testCaseType);
        this.throwableExpectations = throwableExpectations;
    }

    @Override
    protected SingleTestCase testCase(P param) {
        return new SingleExceptionTestCase<>(throwableExpectations,
                formatDesc(description, param), toTestClosure(param), testCaseType);
    }
}
