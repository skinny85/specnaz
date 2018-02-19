package org.specnaz.params;

import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedExceptionTest5;
import org.specnaz.utils.ThrowableExpectations;

import java.util.stream.Stream;

/**
 * The class returned from {@link ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams5)}.
 * You need to call one of the {@code provided} methods on it,
 * passing the parameters for the test to run with.
 *
 * @see #provided(Params5...)
 * @see #provided(Iterable)
 */
public final class ParamsExpectedException5<T extends Throwable, P1, P2, P3, P4, P5> {
    private final ParametrizedExceptionTest5<T, P1, P2, P3, P4, P5> parametrizedTest;

    public ParamsExpectedException5(ParametrizedExceptionTest5<T, P1, P2, P3, P4, P5> parametrizedTest) {
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
    public final ThrowableExpectations<T> provided(Params5<P1, P2, P3, P4, P5>... params) {
        return Conversions.complete5e(parametrizedTest, Stream.of(params));
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
    public ThrowableExpectations<T> provided(Iterable<Params5<P1, P2, P3, P4, P5>> params) {
        return Conversions.complete5e(parametrizedTest, Conversions.iterable2stream(params));
    }
}
