package org.specnaz.examples.custom_dsl.given_when_then.standalone;

import org.specnaz.core.SpecnazCoreDsl;
import org.specnaz.examples.custom_dsl.given_when_then.GivenBuilder;
import org.specnaz.examples.custom_dsl.given_when_then.GivenWhenThenBuilder;

import java.util.function.Consumer;

class GivenWhenThenCoreWrapper implements SpecnazCoreDsl {
    private final String description;
    private final Consumer<GivenBuilder> specClosure;

    public GivenWhenThenCoreWrapper(String description, Consumer<GivenBuilder> specClosure) {
        this.description = description;
        this.specClosure = specClosure;
    }

    public void callSpecification() {
        specification(description, coreDslBuilder -> {
            specClosure.accept(new GivenWhenThenBuilder(coreDslBuilder));
        });
    }
}
