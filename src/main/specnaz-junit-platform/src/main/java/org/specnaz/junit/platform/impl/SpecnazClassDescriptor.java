package org.specnaz.junit.platform.impl;

import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.support.descriptor.ClassSource;
import org.specnaz.impl.SpecParser;
import org.specnaz.impl.TestsGroup;
import org.specnaz.impl.TreeNode;

public final class SpecnazClassDescriptor extends SpecnazClassOrGroupDescriptor {
    private final boolean runOnlyFocusedTests;

    public SpecnazClassDescriptor(SpecnazEngineDescriptor engineDescriptor,
            Class<?> classs, SpecParser specParser) {
        super(engineDescriptor.getUniqueId().append("class", classs.getCanonicalName()),
                classs.getSimpleName(), ClassSource.from(classs));

        engineDescriptor.attach(this);

        if (specParser != null) {
            TreeNode<TestsGroup> testPlan = specParser.testsPlan();
            this.runOnlyFocusedTests = testPlan.value.containsFocusedTests;
            walkTestsGroupTree(testPlan, this);
        } else {
            this.runOnlyFocusedTests = false;
        }
    }

    public void execute(EngineExecutionListener listener) {
        listener.executionStarted(this);

        for (SpecnazGroupDescriptor groupDescriptor : groupDescriptors()) {
            groupDescriptor.execute(listener);
        }

        listener.executionFinished(this, TestExecutionResult.successful());
    }

    private void walkTestsGroupTree(TreeNode<TestsGroup> testsGroupTreeNode, SpecnazClassOrGroupDescriptor parent) {
        SpecnazGroupDescriptor specnazGroupDescriptor = new SpecnazGroupDescriptor(parent, testsGroupTreeNode,
                this.runOnlyFocusedTests);
        for (TreeNode<TestsGroup> childTestsGroupTreeNode : testsGroupTreeNode.children()) {
            walkTestsGroupTree(childTestsGroupTreeNode, specnazGroupDescriptor);
        }
    }

    @Override
    public void addChild(TestDescriptor child) {
        throw new UnsupportedOperationException("addChild() is not supported for SpecnazClassDescriptor");
    }
}
