package org.specnaz.junit.platform;

import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.UniqueId;
import org.junit.platform.engine.support.descriptor.EngineDescriptor;
import org.junit.platform.engine.support.hierarchical.Node;

public final class SpecnazEngineDescriptor2 extends EngineDescriptor
        implements Node<SpecnazEngineExecutionContext> {
    public SpecnazEngineDescriptor2(UniqueId uniqueId) {
        super(uniqueId, "Specnaz JUnit Platform");
    }

    void attach(SpecnazClassDescriptor2 classDescriptor) {
        super.addChild(classDescriptor);
    }

    @Override
    public void addChild(TestDescriptor child) {
        throw new UnsupportedOperationException("addChild() is not supported for SpecnazEngineDescriptor");
    }
}
