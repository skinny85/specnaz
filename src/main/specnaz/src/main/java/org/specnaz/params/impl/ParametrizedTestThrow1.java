package org.specnaz.params.impl;

import org.specnaz.impl.SingleExceptionTestCase;
import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams1;
import org.specnaz.utils.ThrowableExpectations;

public final class ParametrizedTestThrow1<T extends Throwable, P> extends AbstractParametrizedTest1<P> {
    private final ThrowableExpectations<T> throwableExpectations;

    public ParametrizedTestThrow1(ThrowableExpectations<T> throwableExpectations,
            String description, TestClosureParams1<P> testBody) {
        super(description, testBody);
        this.throwableExpectations = throwableExpectations;
    }

    @Override
    protected SingleTestCase testCase(P param) {
        return new SingleExceptionTestCase<T>(new ThrowableExpectations.Wrapper<>(throwableExpectations),
                formatDesc(description, param), toTestClosure(param), TestCaseType.REGULAR);
    }
}
