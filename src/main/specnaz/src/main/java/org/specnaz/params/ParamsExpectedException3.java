package org.specnaz.params;

import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedExceptionTest3;
import org.specnaz.utils.ThrowableExpectations;

import java.util.stream.Stream;

/**
 * The class returned from {@link ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams3)}.
 * You need to call one of the {@code provided} methods on it,
 * passing the parameters for the test to run with.
 *
 * @see #provided(Params3...)
 * @see #provided(Iterable)
 */
public final class ParamsExpectedException3<T extends Throwable, P1, P2, P3> {
    private final ParametrizedExceptionTest3<T, P1, P2, P3> parametrizedTest;

    public ParamsExpectedException3(ParametrizedExceptionTest3<T, P1, P2, P3> parametrizedTest) {
        this.parametrizedTest = parametrizedTest;
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
    public final ThrowableExpectations<T> provided(Params3<P1, P2, P3>... params) {
        return Conversions.complete3e(parametrizedTest, Stream.of(params));
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
    public final ThrowableExpectations<T> provided(Iterable<Params3<P1, P2, P3>> params) {
        return Conversions.complete3e(parametrizedTest, Conversions.iterable2stream(params));
    }
}
