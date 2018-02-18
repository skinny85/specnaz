package org.specnaz.params;

import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedSubgroup2;

import java.util.stream.Stream;

/**
 * The class returned from {@link ParamsSpecBuilder#describes(String, RunnableParams2)}.
 * You need call one of the {@code provided} methods on it,
 * passing the parameters for each version of the sub-specification.
 *
 * @see #provided(Params2...)
 * @see #provided(Iterable)
 */
public final class ParamsExpectedSubgroup2<P1, P2> {
    private final ParametrizedSubgroup2<P1, P2> parametrizedSubgroup;

    public ParamsExpectedSubgroup2(ParametrizedSubgroup2<P1, P2> parametrizedSubgroup) {
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
    public final void provided(Params2<P1, P2>... params) {
        Conversions.complete2d(parametrizedSubgroup, Stream.of(params));
    }

    /**
     * Complete the parametrized sub-specification by providing the parameters
     * it will run with.
     *
     * @param params
     *     the parameters to create the sub-specifications with
     */
    public void provided(Iterable<Params2<P1, P2>> params) {
        Conversions.complete2d(parametrizedSubgroup, Conversions.iterable2stream(params));
    }
}
