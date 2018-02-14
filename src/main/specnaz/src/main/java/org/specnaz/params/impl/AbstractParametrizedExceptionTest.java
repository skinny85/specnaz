package org.specnaz.params.impl;

import org.specnaz.impl.SingleExceptionTestCase;
import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.TestCaseType;
import org.specnaz.utils.ThrowableExpectations;

import java.util.List;

public abstract class AbstractParametrizedExceptionTest<T extends Throwable> extends AbstractParametrizedTest {
    public final ThrowableExpectations<T> throwableExpectations;

    public AbstractParametrizedExceptionTest(ThrowableExpectations<T> throwableExpectations,
            String description, TestCaseType testCaseType) {
        super(description, testCaseType);
        this.throwableExpectations = throwableExpectations;
    }

    @Override
    protected SingleTestCase testCase(List<?> params) {
        return new SingleExceptionTestCase<>(throwableExpectations,
                formatDesc(params), toTestClosure(params), testCaseType);
    }
}
