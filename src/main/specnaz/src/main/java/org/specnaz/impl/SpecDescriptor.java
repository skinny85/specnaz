package org.specnaz.impl;

import org.specnaz.core.CoreDslBuilder;

import java.util.function.Consumer;

public final class SpecDescriptor {
    public final String description;
    public final Consumer<CoreDslBuilder> specClosure;

    public SpecDescriptor(String description, Consumer<CoreDslBuilder> specClosure) {
        this.description = description;
        this.specClosure = specClosure;
    }
}
