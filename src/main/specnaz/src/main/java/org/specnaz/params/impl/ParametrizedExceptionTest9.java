package org.specnaz.params.impl;

import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams9;
import org.specnaz.utils.TestClosure;
import org.specnaz.utils.ThrowableExpectations;

import java.util.List;

public final class ParametrizedExceptionTest9<T extends Throwable, P1, P2, P3, P4, P5, P6, P7, P8, P9> extends
        AbstractParametrizedExceptionTest<T> {
    private final TestClosureParams9<P1, P2, P3, P4, P5, P6, P7, P8, P9> testBody;

    public ParametrizedExceptionTest9(ThrowableExpectations<T> throwableExpectations, String description,
            TestClosureParams9<P1, P2, P3, P4, P5, P6, P7, P8, P9> testBody, TestCaseType testCaseType) {
        super(throwableExpectations, description, testCaseType);
        this.testBody = testBody;
    }

    @Override
    protected TestClosure toTestClosure(List<?> params) {
        return Conversions.toTestClosure9(testBody, params);
    }
}
