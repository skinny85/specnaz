package org.specnaz.junit.platform.impl;

import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;
import org.junit.platform.engine.support.descriptor.ClassSource;
import org.specnaz.impl.SpecParser;
import org.specnaz.impl.TestsGroup;
import org.specnaz.impl.TreeNode;

public final class SpecnazClassDescriptor extends AbstractTestDescriptor {
    public SpecnazClassDescriptor(SpecnazEngineDescriptor engineDescriptor,
            Class<?> classs, SpecParser specParser) {
        super(engineDescriptor.getUniqueId().append("class", classs.getCanonicalName()),
                classs.getSimpleName(), ClassSource.from(classs));

        engineDescriptor.attach(this);

        if (specParser != null) {
            walkTestsGroupTree(specParser.testsPlan());
        }
    }

    private void walkTestsGroupTree(TreeNode<TestsGroup> testsGroupTreeNode) {
        new SpecnazGroupDescriptor(this, testsGroupTreeNode);
        for (TreeNode<TestsGroup> childTestsGroupTreeNode : testsGroupTreeNode.children()) {
            walkTestsGroupTree(childTestsGroupTreeNode);
        }
    }

    void attach(SpecnazGroupDescriptor groupDescriptor) {
        super.addChild(groupDescriptor);
    }

    public void execute(EngineExecutionListener listener) {
        listener.executionStarted(this);

        for (SpecnazGroupDescriptor groupDescriptor : groupDescriptors()) {
            groupDescriptor.execute(listener);
        }

        listener.executionFinished(this, TestExecutionResult.successful());
    }

    @Override
    public void addChild(TestDescriptor child) {
        throw new UnsupportedOperationException("addChild() is not supported for SpecnazClassDescriptor");
    }

    private Iterable<SpecnazGroupDescriptor> groupDescriptors() {
        // safe because we ban children other than SpecnazGroupDescriptor by overriding addChild()
        return (Iterable<SpecnazGroupDescriptor>) getChildren();
    }

    @Override
    public Type getType() {
        return Type.CONTAINER;
    }
}
