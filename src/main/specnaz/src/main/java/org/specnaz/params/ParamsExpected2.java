package org.specnaz.params;

import org.specnaz.TestSettings;
import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedPositiveTest2;

import java.util.stream.Stream;

/**
 * The class returned from {@link ParamsSpecBuilder#should(String, TestClosureParams2)}.
 * You need to call one of the {@code provided} methods on it,
 * passing the parameters for the test to run with.
 *
 * @see #provided(Params2...)
 */
public final class ParamsExpected2<P1, P2> {
    private final ParametrizedPositiveTest2<P1, P2> parametrizedTest;

    public ParamsExpected2(ParametrizedPositiveTest2<P1, P2> parametrizedTest) {
        this.parametrizedTest = parametrizedTest;
    }

    /**
     * Complete the parametrized test by providing the parameters
     * it will run with.
     *
     * @param params
     *     the parameters to run the test with
     * @return
     *     an instance of the {@link TestSettings} class, like a regular
     *     {@link org.specnaz.SpecBuilder#should} method would
     */
    @SafeVarargs
    public final TestSettings provided(Params2<P1, P2>... params) {
        return Conversions.complete2p(parametrizedTest, Stream.of(params));
    }

    /**
     * Complete the parametrized test by providing the parameters
     * it will run with.
     *
     * @param params
     *     the parameters to run the test with
     * @return
     *     an instance of the {@link TestSettings} class, like a regular
     *     {@link org.specnaz.SpecBuilder#should} method would
     */
    public final TestSettings provided(Iterable<Params2<P1, P2>> params) {
        return Conversions.complete2p(parametrizedTest, Conversions.iterable2stream(params));
    }
}
