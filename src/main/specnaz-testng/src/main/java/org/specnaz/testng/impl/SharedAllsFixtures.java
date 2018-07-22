package org.specnaz.testng.impl;

import org.specnaz.impl.Executable;

public final class SharedAllsFixtures {
    private final Executable executable;
    private final long threshold;
    private long executions;
    private boolean executed;
    private Throwable throwable;

    public SharedAllsFixtures(Executable executable, long threshold) {
        this.executable = executable;
        this.threshold = threshold;
        this.executions = 0;
        this.executed = false;
        this.throwable = null;
    }

    public synchronized Throwable execute() {
        if (++executions >= threshold) {
            if (!executed) {
                executed = true;
                throwable = executable.execute();
            }

            return throwable;
        }
        return null;
    }
}
