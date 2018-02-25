package org.specnaz.params.impl;

import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams7;
import org.specnaz.utils.TestClosure;
import org.specnaz.utils.ThrowableExpectations;

import java.util.List;

public final class ParametrizedExceptionTest7<T extends Throwable, P1, P2, P3, P4, P5, P6, P7> extends
        AbstractParametrizedExceptionTest<T> {
    private final TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody;

    public ParametrizedExceptionTest7(ThrowableExpectations<T> throwableExpectations, String description,
            TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody, TestCaseType testCaseType) {
        super(throwableExpectations, description, testCaseType);
        this.testBody = testBody;
    }

    @Override
    protected TestClosure toTestClosure(List<?> params) {
        return Conversions.toTestClosure7(testBody, params);
    }
}
