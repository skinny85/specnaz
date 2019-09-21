package org.specnaz.junit.platform;

import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;
import org.junit.platform.engine.support.descriptor.ClassSource;
import org.specnaz.impl.SpecParser;

public final class SpecnazClassDescriptor extends AbstractTestDescriptor {
    public SpecnazClassDescriptor(SpecnazEngineDescriptor engineDescriptor,
            Class<?> classs, SpecParser specParser) {
        super(engineDescriptor.getUniqueId().append("class", classs.getCanonicalName()),
                classs.getSimpleName(), ClassSource.from(classs));

        engineDescriptor.attach(this);

        if (specParser != null) {
            new SpecnazRootDescribeDescriptor(this, specParser);
        }
    }

    void attach(SpecnazRootDescribeDescriptor rootDescribeDescriptor) {
        super.addChild(rootDescribeDescriptor);
    }

    public void execute(EngineExecutionListener listener) {
        listener.executionStarted(this);

        for (SpecnazRootDescribeDescriptor rootDescribeDescriptor : rootDescribeDescriptors()) {
            rootDescribeDescriptor.execute(listener);
        }

        listener.executionFinished(this, TestExecutionResult.successful());
    }

    @Override
    public void addChild(TestDescriptor child) {
        throw new UnsupportedOperationException("addChild() is not supported for SpecnazClassDescriptor");
    }

    private Iterable<SpecnazRootDescribeDescriptor> rootDescribeDescriptors() {
        // safe because we ban children other than SpecnazRootDescribeDescriptor by overriding addChild()
        return (Iterable<SpecnazRootDescribeDescriptor>) getChildren();
    }

    @Override
    public Type getType() {
        return Type.CONTAINER;
    }
}
