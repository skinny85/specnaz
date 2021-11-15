package org.specnaz.params;

import org.specnaz.TestSettings;
import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedPositiveTest1;

import java.util.stream.Stream;

/**
 * The class returned from {@link ParamsSpecBuilder#should(String, TestClosureParams1)}.
 * You need to call one of the {@code provided} methods on it,
 * passing the parameters for the test to run with.
 *
 * @see #provided
 * @see #provided(Iterable)
 */
public final class ParamsExpected1<P> {
    private final ParametrizedPositiveTest1<P> parametrizedTest;

    public ParamsExpected1(ParametrizedPositiveTest1<P> parametrizedTest) {
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
    public final TestSettings provided(P... params) {
        return Conversions.complete1p(parametrizedTest, Stream.of(params));
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
    public final TestSettings provided(Iterable<? extends P> params) {
        return Conversions.complete1p(parametrizedTest, Conversions.iterable2stream(params));
    }
}
