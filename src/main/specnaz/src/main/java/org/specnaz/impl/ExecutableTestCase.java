package org.specnaz.impl;

public final class ExecutableTestCase {
    public final SingleTestCase testCase;
    public final boolean ignored;
    public final Executable executable;

    public ExecutableTestCase(SingleTestCase testCase) {
        this(testCase, true, null);
    }

    public ExecutableTestCase(SingleTestCase testCase, Executable executable) {
        this(testCase, false, executable);
    }

    private ExecutableTestCase(SingleTestCase testCase, boolean ignored, Executable executable) {
        this.testCase = testCase;
        this.ignored = ignored;
        this.executable = executable;
    }
}
