package org.specnaz.params.impl;

import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams6;
import org.specnaz.utils.TestClosure;
import org.specnaz.utils.ThrowableExpectations;

import java.util.List;

public final class ParametrizedExceptionTest6<T extends Throwable, P1, P2, P3, P4, P5, P6> extends
        AbstractParametrizedExceptionTest<T> {
    private final TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody;

    public ParametrizedExceptionTest6(ThrowableExpectations<T> throwableExpectations, String description,
            TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody, TestCaseType testCaseType) {
        super(throwableExpectations, description, testCaseType);
        this.testBody = testBody;
    }

    @Override
    protected TestClosure toTestClosure(List<?> params) {
        return Conversions.toTestClosure6(testBody, params);
    }
}
