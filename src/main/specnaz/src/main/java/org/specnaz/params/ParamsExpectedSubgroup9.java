package org.specnaz.params;

import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedSubgroup9;

import java.util.stream.Stream;

/**
 * The class returned from {@link ParamsSpecBuilder#describes(String, RunnableParams9)}.
 * You need call one of the {@code provided} methods on it,
 * passing the parameters for each version of the sub-specification.
 *
 * @see #provided(Params9...)
 * @see #provided(Iterable)
 */
public final class ParamsExpectedSubgroup9<P1, P2, P3, P4, P5, P6, P7, P8, P9> {
    private final ParametrizedSubgroup9<P1, P2, P3, P4, P5, P6, P7, P8, P9> parametrizedSubgroup;

    public ParamsExpectedSubgroup9(ParametrizedSubgroup9<P1, P2, P3, P4, P5, P6, P7, P8, P9> parametrizedSubgroup) {
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
    public final void provided(Params9<P1, P2, P3, P4, P5, P6, P7, P8, P9>... params) {
        Conversions.complete9d(parametrizedSubgroup, Stream.of(params));
    }

    /**
     * Complete the parametrized sub-specification by providing the parameters
     * it will run with.
     *
     * @param params
     *     the parameters to create the sub-specifications with
     */
    public void provided(Iterable<Params9<P1, P2, P3, P4, P5, P6, P7, P8, P9>> params) {
        Conversions.complete9d(parametrizedSubgroup, Conversions.iterable2stream(params));
    }
}
