package org.specnaz.params.impl;

import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams3;
import org.specnaz.utils.TestClosure;
import org.specnaz.utils.ThrowableExpectations;

import java.util.List;

public final class ParametrizedExceptionTest3<T extends Throwable, P1, P2, P3> extends
        AbstractParametrizedExceptionTest<T> {
    private final TestClosureParams3<P1, P2, P3> testBody;

    public ParametrizedExceptionTest3(ThrowableExpectations<T> throwableExpectations,
            String description, TestClosureParams3<P1, P2, P3> testBody, TestCaseType testCaseType) {
        super(throwableExpectations, description, testCaseType);
        this.testBody = testBody;
    }

    @Override
    protected TestClosure toTestClosure(List<?> params) {
        return Conversions.toTestClosure3(testBody, params);
    }
}
