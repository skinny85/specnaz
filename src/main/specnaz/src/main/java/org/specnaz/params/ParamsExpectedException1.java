package org.specnaz.params;

import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedExceptionTest1;
import org.specnaz.utils.ThrowableExpectations;

import java.util.stream.Stream;

/**
 * The class returned from {@link ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams1)}.
 * You need to call one of the {@code provided} methods on it,
 * passing the parameters for the test to run with.
 *
 * @see #provided(P...)
 */
public final class ParamsExpectedException1<T extends Throwable, P> {
    private final ParametrizedExceptionTest1<T, P> parametrizedTest;

    public ParamsExpectedException1(ParametrizedExceptionTest1<T, P> parametrizedTest) {
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
    public final ThrowableExpectations<T> provided(P... params) {
        return Conversions.complete1e(parametrizedTest, Stream.of(params));
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
    public final ThrowableExpectations<T> provided(Iterable<? extends P> params) {
        return Conversions.complete1e(parametrizedTest, Conversions.iterable2stream(params));
    }
}
