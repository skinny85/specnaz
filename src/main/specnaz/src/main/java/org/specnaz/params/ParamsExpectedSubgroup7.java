package org.specnaz.params;

import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedSubgroup7;

import java.util.stream.Stream;

/**
 * The class returned from {@link ParamsSpecBuilder#describes(String, RunnableParams7)}.
 * You need call one of the {@code provided} methods on it,
 * passing the parameters for each version of the sub-specification.
 *
 * @see #provided(Params7...)
 * @see #provided(Iterable)
 */
public final class ParamsExpectedSubgroup7<P1, P2, P3, P4, P5, P6, P7> {
    private final ParametrizedSubgroup7<P1, P2, P3, P4, P5, P6, P7> parametrizedSubgroup;

    public ParamsExpectedSubgroup7(ParametrizedSubgroup7<P1, P2, P3, P4, P5, P6, P7> parametrizedSubgroup) {
        this.parametrizedSubgroup = parametrizedSubgroup;
    }

    /**
     * Complete the parametrized sub-specification by providing the parameters
     * it will run with.
     *
     * @param params
     *     the parameters to create the sub-specifications with
     */
    @SafeVarargs
    public final void provided(Params7<P1, P2, P3, P4, P5, P6, P7>... params) {
        Conversions.complete7d(parametrizedSubgroup, Stream.of(params));
    }

    /**
     * Complete the parametrized sub-specification by providing the parameters
     * it will run with.
     *
     * @param params
     *     the parameters to create the sub-specifications with
     */
    public void provided(Iterable<Params7<P1, P2, P3, P4, P5, P6, P7>> params) {
        Conversions.complete7d(parametrizedSubgroup, Conversions.iterable2stream(params));
    }
}
