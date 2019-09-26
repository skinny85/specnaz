package org.specnaz.junit.platform.impl;

import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.UniqueId;
import org.junit.platform.engine.support.descriptor.EngineDescriptor;

public final class SpecnazEngineDescriptor extends EngineDescriptor {
    public SpecnazEngineDescriptor(UniqueId uniqueId) {
        super(uniqueId, "Specnaz JUnit Platform");
    }

    void attach(SpecnazClassDescriptor classDescriptor) {
        super.addChild(classDescriptor);
    }

    public void execute(EngineExecutionListener listener) {
        listener.executionStarted(this);

        for (SpecnazClassDescriptor classDescriptor : classDescriptors()) {
            classDescriptor.execute(listener);
        }

        listener.executionFinished(this, TestExecutionResult.successful());
    }

    @Override
    public void addChild(TestDescriptor child) {
        throw new UnsupportedOperationException("addChild() is not supported for SpecnazEngineDescriptor");
    }

    private Iterable<SpecnazClassDescriptor> classDescriptors() {
        // safe because we ban children other than SpecnazClassDescriptors by overriding addChild()
        return (Iterable<SpecnazClassDescriptor>) getChildren();
    }
}
