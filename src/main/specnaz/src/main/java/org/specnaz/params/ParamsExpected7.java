package org.specnaz.params;

import org.specnaz.TestSettings;
import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedPositiveTest7;

import java.util.stream.Stream;

/**
 * The class returned from {@link ParamsSpecBuilder#should(String, TestClosureParams7)}.
 * You need to call one of the {@code provided} methods on it,
 * passing the parameters for the test to run with.
 *
 * @see #provided(Params7...)
 * @see #provided(Iterable)
 */
public final class ParamsExpected7<P1, P2, P3, P4, P5, P6, P7> {
    private final ParametrizedPositiveTest7<P1, P2, P3, P4, P5, P6, P7> parametrizedTest;

    public ParamsExpected7(ParametrizedPositiveTest7<P1, P2, P3, P4, P5, P6, P7> parametrizedTest) {
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
    public final TestSettings provided(Params7<P1, P2, P3, P4, P5, P6, P7>... params) {
        return Conversions.complete7p(parametrizedTest, Stream.of(params));
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
    public TestSettings provided(Iterable<Params7<P1, P2, P3, P4, P5, P6, P7>> params) {
        return Conversions.complete7p(parametrizedTest, Conversions.iterable2stream(params));
    }
}
