package org.specnaz.params;

import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedExceptionTest2;
import org.specnaz.utils.ThrowableExpectations;

/**
 * The class returned from {@link ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams2)}.
 * You need to call one of the {@code provided} methods on it,
 * passing the parameters for the test to run with.
 *
 * @see #provided(Params2...)
 */
public final class ParamsExpectedException2<T extends Throwable, P1, P2> {
    private final ParametrizedExceptionTest2<T, P1, P2> parametrizedTest;
    private final ThrowableExpectations<T> throwableExpectations;

    public ParamsExpectedException2(ParametrizedExceptionTest2<T, P1, P2> parametrizedTest,
            ThrowableExpectations<T> throwableExpectations) {
        this.parametrizedTest = parametrizedTest;
        this.throwableExpectations = throwableExpectations;
    }

    /**
     * Complete the parametrized test by providing the parameters
     * it will run with.
     *
     * @param params
     *     the parameters to run the test with
     * @return
     *     an instance of the {@link ThrowableExpectations} class, like a regular
     *     {@link org.specnaz.SpecBuilder#shouldThrow} method would
     */
    @SafeVarargs
    public final ThrowableExpectations<T> provided(Params2<P1, P2>... params) {
        Conversions.complete2(parametrizedTest, params);
        return throwableExpectations;
    }
}
