package org.specnaz.impl;

@FunctionalInterface
public interface ExecutionClosure {
    void execute() throws Throwable;
}
