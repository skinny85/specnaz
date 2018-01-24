package org.specnaz.impl;

public final class ExecutableTestCase {
    public final SingleTestCase testCase;
    private final Executable executable;

    public ExecutableTestCase(SingleTestCase testCase) {
        this(testCase, null);
    }

    public ExecutableTestCase(SingleTestCase testCase, Executable executable) {
        this.testCase = testCase;
        this.executable = executable;
    }

    public boolean isIgnored() {
        return executable == null;
    }

    public Throwable execute() {
        return executable.execute();
    }
}
