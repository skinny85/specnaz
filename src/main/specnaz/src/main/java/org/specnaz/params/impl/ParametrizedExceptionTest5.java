package org.specnaz.params.impl;

import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams5;
import org.specnaz.utils.TestClosure;
import org.specnaz.utils.ThrowableExpectations;

import java.util.List;

public final class ParametrizedExceptionTest5<T extends Throwable, P1, P2, P3, P4, P5> extends
        AbstractParametrizedExceptionTest<T> {
    private final TestClosureParams5<P1, P2, P3, P4, P5> testBody;

    public ParametrizedExceptionTest5(ThrowableExpectations<T> throwableExpectations, String description,
            TestClosureParams5<P1, P2, P3, P4, P5> testBody, TestCaseType testCaseType) {
        super(throwableExpectations, description, testCaseType);
        this.testBody = testBody;
    }

    @Override
    protected TestClosure toTestClosure(List<?> params) {
        return Conversions.toTestClosure5(testBody, params);
    }
}
