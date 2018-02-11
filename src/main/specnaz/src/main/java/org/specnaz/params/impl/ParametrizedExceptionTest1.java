package org.specnaz.params.impl;

import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams1;
import org.specnaz.utils.TestClosure;
import org.specnaz.utils.ThrowableExpectations;

import java.util.List;

public final class ParametrizedExceptionTest1<T extends Throwable, P> extends AbstractParametrizedExceptionTest<T> {
    private final TestClosureParams1<P> testBody;

    public ParametrizedExceptionTest1(ThrowableExpectations<T> throwableExpectations,
            String description, TestClosureParams1<P> testBody, TestCaseType testCaseType) {
        super(throwableExpectations, description, testCaseType);
        this.testBody = testBody;
    }

    @Override
    protected TestClosure toTestClosure(List<?> params) {
        return () -> testBody.invoke((P) params.get(0));
    }
}
