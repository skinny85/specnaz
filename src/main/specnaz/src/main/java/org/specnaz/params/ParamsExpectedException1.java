package org.specnaz.params;

import org.specnaz.params.impl.ParametrizedExceptionTest1;
import org.specnaz.utils.ThrowableExpectations;

public final class ParamsExpectedException1<T extends Throwable, P> {
    private final ParametrizedExceptionTest1<T, P> parametrizedTest;
    private final ThrowableExpectations<T> throwableExpectations;

    public ParamsExpectedException1(ParametrizedExceptionTest1<T, P> parametrizedTest,
            ThrowableExpectations<T> throwableExpectations) {
        this.parametrizedTest = parametrizedTest;
        this.throwableExpectations = throwableExpectations;
    }

    @SafeVarargs
    public final ThrowableExpectations<T> provided(P... params) {
        parametrizedTest.complete(params);
        return throwableExpectations;
    }
}
