package org.specnaz.impl;

import org.specnaz.core.CoreDslBuilder;

import java.util.function.Consumer;

public final class SpecDescriptor {
    public final String description;
    public final boolean ignoredTestGroup;
    public final Consumer<CoreDslBuilder> specClosure;

    public SpecDescriptor(String description, boolean ignoredTestGroup, Consumer<CoreDslBuilder> specClosure) {
        this.description = description;
        this.ignoredTestGroup = ignoredTestGroup;
        this.specClosure = specClosure;
    }
}
