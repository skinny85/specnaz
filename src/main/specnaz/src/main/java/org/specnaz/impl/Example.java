package org.specnaz.impl;

public abstract class Example {
    public final SingleTestCase testCase;

    Example(SingleTestCase testCase) {
        this.testCase = testCase;
    }

    public abstract boolean focused();
}
