package org.specnaz.params;

import org.specnaz.TestSettings;
import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedPositiveTest2;

/**
 * The class returned from {@link ParamsSpecBuilder#should(String, TestClosureParams2)}.
 * You need to call one of the {@code provided} methods on it,
 * passing the parameters for the test to run with.
 *
 * @see #provided(Params2...)
 */
public final class ParamsExpected2<P1, P2> {
    private final ParametrizedPositiveTest2<P1, P2> parametrizedTest;
    private final TestSettings testSettings;

    public ParamsExpected2(ParametrizedPositiveTest2<P1, P2> parametrizedTest, TestSettings testSettings) {
        this.parametrizedTest = parametrizedTest;
        this.testSettings = testSettings;
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
        Conversions.complete2(parametrizedTest, params);
        return testSettings;
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
        Conversions.complete2(parametrizedTest, params);
        return testSettings;
    }
}
