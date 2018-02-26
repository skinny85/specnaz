package org.specnaz.params.impl;

import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams8;
import org.specnaz.utils.TestClosure;
import org.specnaz.utils.ThrowableExpectations;

import java.util.List;

public final class ParametrizedExceptionTest8<T extends Throwable, P1, P2, P3, P4, P5, P6, P7, P8> extends
        AbstractParametrizedExceptionTest<T> {
    private final TestClosureParams8<P1, P2, P3, P4, P5, P6, P7, P8> testBody;

    public ParametrizedExceptionTest8(ThrowableExpectations<T> throwableExpectations, String description,
            TestClosureParams8<P1, P2, P3, P4, P5, P6, P7, P8> testBody, TestCaseType testCaseType) {
        super(throwableExpectations, description, testCaseType);
        this.testBody = testBody;
    }

    @Override
    protected TestClosure toTestClosure(List<?> params) {
        return Conversions.toTestClosure8(testBody, params);
    }
}
