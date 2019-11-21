package org.specnaz.junit.platform.impl;

import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.TestSource;
import org.junit.platform.engine.UniqueId;
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class SpecnazClassOrGroupDescriptor extends AbstractTestDescriptor {
    private final List<SpecnazGroupDescriptor> childGroupDescriptors = new LinkedList<>();

    public SpecnazClassOrGroupDescriptor(UniqueId uniqueId, String displayName, TestSource source) {
        super(uniqueId, displayName, source);
    }

    public SpecnazClassOrGroupDescriptor(UniqueId uniqueId, String displayName) {
        super(uniqueId, displayName);
    }

    void attach(SpecnazGroupDescriptor groupDescriptor) {
        super.addChild(groupDescriptor);
        this.childGroupDescriptors.add(groupDescriptor);
    }

    public void execute(EngineExecutionListener listener) {
        listener.executionStarted(this);

        for (SpecnazGroupDescriptor groupDescriptor : groupDescriptors()) {
            groupDescriptor.execute(listener);
        }

        listener.executionFinished(this, TestExecutionResult.successful());
    }

    protected Iterable<SpecnazGroupDescriptor> groupDescriptors() {
        return Collections.unmodifiableList(this.childGroupDescriptors);
    }

    @Override
    public Type getType() {
        return Type.CONTAINER;
    }
}
