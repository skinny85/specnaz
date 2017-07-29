package org.specnaz.impl;

public final class UnfocusedExample extends Example {
    public UnfocusedExample(SingleTestCase testCase) {
        super(testCase);
    }

    @Override
    public boolean focused() {
        return false;
    }
}
