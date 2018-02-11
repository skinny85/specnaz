package org.specnaz.params.impl;

import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams2;
import org.specnaz.utils.TestClosure;
import org.specnaz.utils.ThrowableExpectations;

import java.util.List;

public final class ParametrizedExceptionTest2<T extends Throwable, P1, P2> extends
        AbstractParametrizedExceptionTest<T> {
    private final TestClosureParams2<P1, P2> testBody;

    public ParametrizedExceptionTest2(ThrowableExpectations<T> throwableExpectations,
            String description, TestClosureParams2<P1, P2> testBody, TestCaseType testCaseType) {
        super(throwableExpectations, description, testCaseType);
        this.testBody = testBody;
    }

    @Override
    protected TestClosure toTestClosure(List<?> params) {
        return () -> testBody.invoke((P1) params.get(0), (P2) params.get(1));
    }
}
