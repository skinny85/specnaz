package org.specnaz.params;

import org.specnaz.impl.SpecsRegistry;
import org.specnaz.impl.SpecsRegistryViolation;
import org.specnaz.params.impl.ParamsSpecBuilderCoreDslAdapter;

import java.util.function.Consumer;

public interface SpecnazParams {
    default void describes(String description, Consumer<ParamsSpecBuilder> specClosure) {
        try {
            SpecsRegistry.register(this, description, true, coreDslBuilder -> {
                specClosure.accept(new ParamsSpecBuilderCoreDslAdapter(coreDslBuilder));
            });
        } catch (SpecsRegistryViolation e) {
            throw new IllegalStateException("Specnaz.describes() was called multiple times in the " +
                    "no-argument constructor of " + this.getClass().getSimpleName());
        }
    }
}
