package org.specnaz.params;

import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedSubgroup4;

import java.util.stream.Stream;

/**
 * The class returned from {@link ParamsSpecBuilder#describes(String, RunnableParams4)}.
 * You need call one of the {@code provided} methods on it,
 * passing the parameters for each version of the sub-specification.
 *
 * @see #provided(Params4...)
 * @see #provided(Iterable)
 */
public final class ParamsExpectedSubgroup4<P1, P2, P3, P4> {
    private final ParametrizedSubgroup4<P1, P2, P3, P4> parametrizedSubgroup;

    public ParamsExpectedSubgroup4(ParametrizedSubgroup4<P1, P2, P3, P4> parametrizedSubgroup) {
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
    public final void provided(Params4<P1, P2, P3, P4>... params) {
        Conversions.complete4d(parametrizedSubgroup, Stream.of(params));
    }

    /**
     * Complete the parametrized sub-specification by providing the parameters
     * it will run with.
     *
     * @param params
     *     the parameters to create the sub-specifications with
     */
    public void provided(Iterable<Params4<P1, P2, P3, P4>> params) {
        Conversions.complete4d(parametrizedSubgroup, Conversions.iterable2stream(params));
    }
}
