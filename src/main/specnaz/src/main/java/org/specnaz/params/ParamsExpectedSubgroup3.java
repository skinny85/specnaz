package org.specnaz.params;

import org.specnaz.params.impl.ParametrizedSubgroup3;
import org.specnaz.params.impl.Conversions;

import java.util.stream.Stream;

/**
 * The class returned from {@link ParamsSpecBuilder#describes(String, RunnableParams3)}.
 * You need call one of the {@code provided} methods on it,
 * passing the parameters for each version of the sub-specification.
 *
 * @see #provided(Params3...)
 * @see #provided(Iterable)
 */
public final class ParamsExpectedSubgroup3<P1, P2, P3> {
    private final ParametrizedSubgroup3<P1, P2, P3> parametrizedSubgroup;

    public ParamsExpectedSubgroup3(ParametrizedSubgroup3<P1, P2, P3> parametrizedSubgroup) {
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
    public final void provided(Params3<P1, P2, P3>... params) {
        Conversions.complete3d(parametrizedSubgroup, Stream.of(params));
    }

    /**
     * Complete the parametrized sub-specification by providing the parameters
     * it will run with.
     *
     * @param params
     *     the parameters to create the sub-specifications with
     */
    public void provided(Iterable<Params3<P1, P2, P3>> params) {
        Conversions.complete3d(parametrizedSubgroup, Conversions.iterable2stream(params));
    }
}
