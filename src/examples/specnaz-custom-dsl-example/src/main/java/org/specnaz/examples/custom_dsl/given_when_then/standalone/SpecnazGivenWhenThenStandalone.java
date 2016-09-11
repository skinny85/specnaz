package org.specnaz.examples.custom_dsl.given_when_then.standalone;

import org.specnaz.examples.custom_dsl.given_when_then.GivenBuilder;

import java.util.function.Consumer;

public interface SpecnazGivenWhenThenStandalone {
    default void given(String description, Consumer<GivenBuilder> specClosure) {
        try {
            GivenWhenThenRegistry.add(this, "Given " + description, specClosure);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("SpecnazGivenWhenThenStandalone.given() was called multiple times in the " +
                    "no-argument constructor of " + this.getClass().getSimpleName());
        }
    }
}
