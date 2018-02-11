package org.specnaz.params;

import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedExceptionTest2;
import org.specnaz.utils.ThrowableExpectations;

public final class ParamsExpectedException2<T extends Throwable, P1, P2> {
    private final ParametrizedExceptionTest2<T, P1, P2> parametrizedTest;
    private final ThrowableExpectations<T> throwableExpectations;

    public ParamsExpectedException2(ParametrizedExceptionTest2<T, P1, P2> parametrizedTest,
            ThrowableExpectations<T> throwableExpectations) {
        this.parametrizedTest = parametrizedTest;
        this.throwableExpectations = throwableExpectations;
    }

    @SafeVarargs
    public final ThrowableExpectations<T> provided(Params2<P1, P2>... params) {
        Conversions.complete2(parametrizedTest, params);
        return throwableExpectations;
    }
}
