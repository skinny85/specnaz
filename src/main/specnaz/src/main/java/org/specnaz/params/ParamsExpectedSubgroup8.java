package org.specnaz.params;

import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedSubgroup8;

import java.util.stream.Stream;

/**
 * The class returned from {@link ParamsSpecBuilder#describes(String, RunnableParams8)}.
 * You need call one of the {@code provided} methods on it,
 * passing the parameters for each version of the sub-specification.
 *
 * @see #provided(Params8...)
 * @see #provided(Iterable)
 */
public final class ParamsExpectedSubgroup8<P1, P2, P3, P4, P5, P6, P7, P8> {
    private final ParametrizedSubgroup8<P1, P2, P3, P4, P5, P6, P7, P8> parametrizedSubgroup;

    public ParamsExpectedSubgroup8(ParametrizedSubgroup8<P1, P2, P3, P4, P5, P6, P7, P8> parametrizedSubgroup) {
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
    public final void provided(Params8<P1, P2, P3, P4, P5, P6, P7, P8>... params) {
        Conversions.complete8d(parametrizedSubgroup, Stream.of(params));
    }

    /**
     * Complete the parametrized sub-specification by providing the parameters
     * it will run with.
     *
     * @param params
     *     the parameters to create the sub-specifications with
     */
    public void provided(Iterable<Params8<P1, P2, P3, P4, P5, P6, P7, P8>> params) {
        Conversions.complete8d(parametrizedSubgroup, Conversions.iterable2stream(params));
    }
}
