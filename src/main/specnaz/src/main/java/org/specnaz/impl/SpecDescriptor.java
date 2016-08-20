package org.specnaz.impl;

import org.specnaz.SpecBuilder;

import java.util.function.Consumer;

public final class SpecDescriptor {
    public final String description;
    public final Consumer<SpecBuilder> specClosure;

    public SpecDescriptor(String description, Consumer<SpecBuilder> specClosure) {
        this.description = description;
        this.specClosure = specClosure;
    }
}
