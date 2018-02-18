package org.specnaz.params;

import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedSubgroup5;

import java.util.stream.Stream;

/**
 * The class returned from {@link ParamsSpecBuilder#describes(String, RunnableParams5)}.
 * You need call one of the {@code provided} methods on it,
 * passing the parameters for each version of the sub-specification.
 *
 * @see #provided(Params5...)
 * @see #provided(Iterable)
 */
public class ParamsExpectedSubgroup5<P1, P2, P3, P4, P5> {
    private final ParametrizedSubgroup5<P1, P2, P3, P4, P5> parametrizedSubgroup;

    public ParamsExpectedSubgroup5(ParametrizedSubgroup5<P1, P2, P3, P4, P5> parametrizedSubgroup) {
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
    public final void provided(Params5<P1, P2, P3, P4, P5>... params) {
        Conversions.complete5d(parametrizedSubgroup, Stream.of(params));
    }

    /**
     * Complete the parametrized sub-specification by providing the parameters
     * it will run with.
     *
     * @param params
     *     the parameters to create the sub-specifications with
     */
    public void provided(Iterable<Params5<P1, P2, P3, P4, P5>> params) {
        Conversions.complete5d(parametrizedSubgroup, Conversions.iterable2stream(params));
    }
}
