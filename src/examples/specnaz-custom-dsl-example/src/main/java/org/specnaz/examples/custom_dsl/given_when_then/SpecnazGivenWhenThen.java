package org.specnaz.examples.custom_dsl.given_when_then;

import org.specnaz.core.CoreDslBuilder;
import org.specnaz.core.SpecnazCoreDsl;

import java.util.function.Consumer;

public interface SpecnazGivenWhenThen extends SpecnazCoreDsl {
    default void given(String description, Consumer<GivenBuilder> closure) {
        SpecnazCoreDsl.super.specification("Given " + description, coreDslBuilder -> {
            closure.accept(new GivenWhenThenBuilder(coreDslBuilder));
        });
    }

    @Override
    @Deprecated
    default void specification(String description, Consumer<CoreDslBuilder> specClosure) {
        throw new UnsupportedOperationException("Use given(description, closure) instead");
    }
}
