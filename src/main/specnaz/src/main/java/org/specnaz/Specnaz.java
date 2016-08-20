package org.specnaz;

import org.specnaz.impl.SpecsRegistry;

import java.util.function.Consumer;

public interface Specnaz {
    default void describes(String description, Consumer<SpecBuilder> specClosure) {
        SpecsRegistry.register(this, description, specClosure);
    }
}
