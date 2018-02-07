package org.specnaz.params;

import org.specnaz.params.impl.ParametrizedTestThrow1;
import org.specnaz.utils.ThrowableExpectations;

public final class ParamsExpectedThrow1<T extends Throwable, P> {
    private final ParametrizedTestThrow1<T, P> parametrizedTest;
    private final ThrowableExpectations<T> throwableExpectations;

    public ParamsExpectedThrow1(ParametrizedTestThrow1<T, P> parametrizedTest,
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
