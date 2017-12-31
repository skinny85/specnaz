package org.specnaz.impl;

public final class ExecutionClosure {
    @FunctionalInterface
    interface Lambda {
        void execute() throws Throwable;
    }

    private final boolean ignored;
    private final Lambda closure;

    public ExecutionClosure() {
        this(true, null);
    }

    public ExecutionClosure(Lambda closure) {
        this(false, closure);
    }

    public ExecutionClosure(boolean ignored, Lambda closure) {
        this.ignored = ignored;
        this.closure = closure;
    }

    public void execute() throws Throwable {
        if (closure != null)
            closure.execute();
    }

    public boolean ignored() {
        return ignored;
    }
}
