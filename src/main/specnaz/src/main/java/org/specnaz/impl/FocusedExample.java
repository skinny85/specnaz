package org.specnaz.impl;

public final class FocusedExample extends Example {
    public FocusedExample(SingleTestCase testCase) {
        super(testCase);
    }

    @Override
    public boolean focused() {
        return true;
    }
}
