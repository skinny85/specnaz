package org.specnaz.params;

import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedExceptionTest8;
import org.specnaz.utils.ThrowableExpectations;

import java.util.stream.Stream;

/**
 * The class returned from {@link ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams8)}.
 * You need to call one of the {@code provided} methods on it,
 * passing the parameters for the test to run with.
 *
 * @see #provided(Params8...)
 * @see #provided(Iterable)
 */
public final class ParamsExpectedException8<T extends Throwable, P1, P2, P3, P4, P5, P6, P7, P8> {
    private final ParametrizedExceptionTest8<T, P1, P2, P3, P4, P5, P6, P7, P8> parametrizedTest;

    public ParamsExpectedException8(ParametrizedExceptionTest8<T, P1, P2, P3, P4, P5, P6, P7, P8> parametrizedTest) {
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
    public final ThrowableExpectations<T> provided(Params8<P1, P2, P3, P4, P5, P6, P7, P8>... params) {
        return Conversions.complete8e(parametrizedTest, Stream.of(params));
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
    public ThrowableExpectations<T> provided(Iterable<Params8<P1, P2, P3, P4, P5, P6, P7, P8>> params) {
        return Conversions.complete8e(parametrizedTest, Conversions.iterable2stream(params));
    }
}
