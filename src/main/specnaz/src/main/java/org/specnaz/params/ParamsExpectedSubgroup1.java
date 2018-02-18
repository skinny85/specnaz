package org.specnaz.params;

import org.specnaz.params.impl.ParametrizedSubgroup1;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ParamsExpectedSubgroup1<P> {
    private final ParametrizedSubgroup1<P> parametrizedSubgroup;

    public ParamsExpectedSubgroup1(ParametrizedSubgroup1<P> parametrizedSubgroup) {
        this.parametrizedSubgroup = parametrizedSubgroup;
    }

    @SafeVarargs
    public final void provided(P... params) {
        parametrizedSubgroup.complete(Stream.of(params)
                .map(p -> Collections.singletonList(p))
                .collect(Collectors.toList()));
    }
}
