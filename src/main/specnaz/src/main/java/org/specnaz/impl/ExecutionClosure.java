package org.specnaz.impl;

public final class ExecutionClosure {
    public final SingleTestCase testCase;
    public final boolean ignored;
    public final Executable executable;

    public ExecutionClosure(SingleTestCase testCase) {
        this(testCase, true, null);
    }

    public ExecutionClosure(SingleTestCase testCase, Executable executable) {
        this(testCase, false, executable);
    }

    private ExecutionClosure(SingleTestCase testCase, boolean ignored, Executable executable) {
        this.testCase = testCase;
        this.ignored = ignored;
        this.executable = executable;
    }
}
