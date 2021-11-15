package org.specnaz.params;

import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedSubgroup1;

import java.util.stream.Stream;

/**
 * The class returned from {@link ParamsSpecBuilder#describes(String, RunnableParams1)}.
 * You need call one of the {@code provided} methods on it,
 * passing the parameters for each version of the sub-specification.
 *
 * @see #provided
 * @see #provided(Iterable)
 */
public final class ParamsExpectedSubgroup1<P> {
    private final ParametrizedSubgroup1<P> parametrizedSubgroup;

    public ParamsExpectedSubgroup1(ParametrizedSubgroup1<P> parametrizedSubgroup) {
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
    public final void provided(P... params) {
        Conversions.complete1d(parametrizedSubgroup, Stream.of(params));
    }

    /**
     * Complete the parametrized sub-specification by providing the parameters
     * it will run with.
     *
     * @param params
     *     the parameters to create the sub-specifications with
     */
    public void provided(Iterable<? extends P> params) {
        Conversions.complete1d(parametrizedSubgroup, Conversions.iterable2stream(params));
    }
}
