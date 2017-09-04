package org.specnaz.impl;

import org.specnaz.core.CoreDslBuilder;

import java.util.function.Consumer;

public final class SpecDescriptor {
    public final String description;
    public final TestCaseType testCaseType;
    public final Consumer<CoreDslBuilder> specClosure;

    public SpecDescriptor(String description, TestCaseType testCaseType, Consumer<CoreDslBuilder> specClosure) {
        this.description = description;
        this.testCaseType = testCaseType;
        this.specClosure = specClosure;
    }
}
