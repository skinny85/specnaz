package org.specnaz.junit.platform.impl;

import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;

public final class AlwaysFailTestDescriptor extends AbstractTestDescriptor {
    private final Throwable error;

    public AlwaysFailTestDescriptor(InvalidSpecnazClassDescriptor specnazClassDescriptor, Throwable error) {
        super(specnazClassDescriptor.getUniqueId().append("test", "error"), "error");

        specnazClassDescriptor.attach(this);

        this.error = error;
    }

    void execute(EngineExecutionListener listener) {
        listener.executionStarted(this);
        listener.executionFinished(this, TestExecutionResult.failed(this.error));
    }

    @Override
    public Type getType() {
        return Type.TEST;
    }
}
