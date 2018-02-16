package org.specnaz.params;

import org.specnaz.TestSettings;
import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedPositiveTest3;

import java.util.stream.Stream;

/**
 * The class returned from {@link ParamsSpecBuilder#should(String, TestClosureParams3)}.
 * You need to call one of the {@code provided} methods on it,
 * passing the parameters for the test to run with.
 *
 * @see #provided(Params3...)
 * @see #provided(Iterable)
 */
public final class ParamsExpected3<P1, P2, P3> {
    private final ParametrizedPositiveTest3<P1, P2, P3> parametrizedTest;

    public ParamsExpected3(ParametrizedPositiveTest3<P1, P2, P3> parametrizedTest) {
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
    public final TestSettings provided(Params3<P1, P2, P3>... params) {
        return Conversions.complete3p(parametrizedTest, Stream.of(params));
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
    public final TestSettings provided(Iterable<Params3<P1, P2, P3>> params) {
        return Conversions.complete3p(parametrizedTest, Conversions.iterable2stream(params));
    }
}
