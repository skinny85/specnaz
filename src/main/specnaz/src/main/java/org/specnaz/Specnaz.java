package org.specnaz;

import java.util.function.Consumer;

public interface Specnaz {
    default void describes(String description, Consumer<SpecBuilder> specClosure) {

    }
}
