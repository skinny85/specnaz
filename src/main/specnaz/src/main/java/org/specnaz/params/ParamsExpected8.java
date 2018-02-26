package org.specnaz.params;

import org.specnaz.TestSettings;
import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedPositiveTest8;

import java.util.stream.Stream;

/**
 * The class returned from {@link ParamsSpecBuilder#should(String, TestClosureParams8)}.
 * You need to call one of the {@code provided} methods on it,
 * passing the parameters for the test to run with.
 *
 * @see #provided(Params8...)
 * @see #provided(Iterable)
 */
public final class ParamsExpected8<P1, P2, P3, P4, P5, P6, P7, P8> {
    private final ParametrizedPositiveTest8<P1, P2, P3, P4, P5, P6, P7, P8> parametrizedTest;

    public ParamsExpected8(ParametrizedPositiveTest8<P1, P2, P3, P4, P5, P6, P7, P8> parametrizedTest) {
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
    public final TestSettings provided(Params8<P1, P2, P3, P4, P5, P6, P7, P8>... params) {
        return Conversions.complete8p(parametrizedTest, Stream.of(params));
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
    public TestSettings provided(Iterable<Params8<P1, P2, P3, P4, P5, P6, P7, P8>> params) {
        return Conversions.complete8p(parametrizedTest, Conversions.iterable2stream(params));
    }
}
