package org.specnaz.params;

import org.specnaz.params.impl.ParametrizedExceptionTest2;
import org.specnaz.utils.ThrowableExpectations;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        parametrizedTest.complete(Stream.of(params).map(p -> Arrays.asList(p._1, p._2)).collect(Collectors.toList()));
        return throwableExpectations;
    }
}
