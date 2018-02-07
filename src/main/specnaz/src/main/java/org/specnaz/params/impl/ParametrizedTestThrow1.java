package org.specnaz.params.impl;

import org.specnaz.impl.SingleExceptionTestCase;
import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams1;

public final class ParametrizedTestThrow1<T extends Throwable, P> extends AbstractParametrizedTest1<P> {
    private final Class<T> expectedException;

    public ParametrizedTestThrow1(Class<T> expectedException, String description, TestClosureParams1<P> testBody) {
        super(description, testBody);
        this.expectedException = expectedException;
    }

    @Override
    protected SingleTestCase testCase(P param) {
        return new SingleExceptionTestCase<>(TestCaseType.REGULAR, expectedException,
                formatDesc(description, param), toTestClosure(param));
    }
}
