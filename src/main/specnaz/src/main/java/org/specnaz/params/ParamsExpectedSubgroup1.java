package org.specnaz.params;

import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedSubgroup1;

import java.util.stream.Stream;

public final class ParamsExpectedSubgroup1<P> {
    private final ParametrizedSubgroup1<P> parametrizedSubgroup;

    public ParamsExpectedSubgroup1(ParametrizedSubgroup1<P> parametrizedSubgroup) {
        this.parametrizedSubgroup = parametrizedSubgroup;
    }

    @SafeVarargs
    public final void provided(P... params) {
        Conversions.complete1d(parametrizedSubgroup, Stream.of(params));
    }

    public void provided(Iterable<? extends P> params) {
        Conversions.complete1d(parametrizedSubgroup, Conversions.iterable2stream(params));
    }
}
