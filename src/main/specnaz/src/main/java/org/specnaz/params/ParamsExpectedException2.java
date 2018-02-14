package org.specnaz.params;

import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedExceptionTest2;
import org.specnaz.utils.ThrowableExpectations;

import java.util.stream.Stream;

/**
 * The class returned from {@link ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams2)}.
 * You need to call one of the {@code provided} methods on it,
 * passing the parameters for the test to run with.
 *
 * @see #provided(Params2...)
 */
public final class ParamsExpectedException2<T extends Throwable, P1, P2> {
    private final ParametrizedExceptionTest2<T, P1, P2> parametrizedTest;

    public ParamsExpectedException2(ParametrizedExceptionTest2<T, P1, P2> parametrizedTest) {
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
    public final ThrowableExpectations<T> provided(Params2<P1, P2>... params) {
        return Conversions.complete2e(parametrizedTest, Stream.of(params));
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
    public final ThrowableExpectations<T> provided(Iterable<Params2<P1, P2>> params) {
        return Conversions.complete2e(parametrizedTest, Conversions.iterable2stream(params));
    }
}
