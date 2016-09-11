package org.specnaz.examples.custom_dsl.given_when_then;

import org.specnaz.utils.TestClosure;

import java.util.function.Consumer;

public interface GivenBuilder {
    default void given(String description, Runnable closure) {
        given(description, () -> {}, closure);
    }

    void given(String description, TestClosure action, Runnable closure);

    void when(String description, TestClosure action, Consumer<ThensBuilder> thens);
}
