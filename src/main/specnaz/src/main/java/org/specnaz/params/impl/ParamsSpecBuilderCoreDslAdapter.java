package org.specnaz.params.impl;

import org.specnaz.core.CoreDslBuilder;
import org.specnaz.impl.SpecBuilderCoreDslAdapter;
import org.specnaz.params.ParamsExpected1;
import org.specnaz.params.ParamsSpecBuilder;
import org.specnaz.params.TestClosureParams1;

public final class ParamsSpecBuilderCoreDslAdapter extends SpecBuilderCoreDslAdapter
        implements ParamsSpecBuilder {
    public ParamsSpecBuilderCoreDslAdapter(CoreDslBuilder coreDslBuilder) {
        super(coreDslBuilder);
    }

    @Override
    public <P> ParamsExpected1<P> should(String description, TestClosureParams1<P> testBody) {
        return coreDslBuilder.parametrizedTest1(shouldDescription(description), testBody);
    }
}
