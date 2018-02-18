package org.specnaz.params;

import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedSubgroup6;

import java.util.stream.Stream;

/**
 * The class returned from {@link ParamsSpecBuilder#describes(String, RunnableParams6)}.
 * You need call one of the {@code provided} methods on it,
 * passing the parameters for each version of the sub-specification.
 *
 * @see #provided(Params6...)
 * @see #provided(Iterable)
 */
public final class ParamsExpectedSubgroup6<P1, P2, P3, P4, P5, P6> {
    private final ParametrizedSubgroup6<P1, P2, P3, P4, P5, P6> parametrizedSubgroup;

    public ParamsExpectedSubgroup6(ParametrizedSubgroup6<P1, P2, P3, P4, P5, P6> parametrizedSubgroup) {
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
    public final void provided(Params6<P1, P2, P3, P4, P5, P6>... params) {
        Conversions.complete6d(parametrizedSubgroup, Stream.of(params));
    }

    /**
     * Complete the parametrized sub-specification by providing the parameters
     * it will run with.
     *
     * @param params
     *     the parameters to create the sub-specifications with
     */
    public void provided(Iterable<Params6<P1, P2, P3, P4, P5, P6>> params) {
        Conversions.complete6d(parametrizedSubgroup, Conversions.iterable2stream(params));
    }
}
