package org.specnaz.junit.platform.impl;

import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.support.descriptor.ClassSource;
import org.specnaz.impl.SpecParser;
import org.specnaz.impl.TestsGroup;
import org.specnaz.impl.TreeNode;

public final class SpecnazClassDescriptor extends SpecnazClassOrGroupDescriptor {
    public SpecnazClassDescriptor(SpecnazEngineDescriptor engineDescriptor,
            Class<?> classs, SpecParser specParser) {
        super(engineDescriptor.getUniqueId().append("class", classs.getCanonicalName()),
                classs.getSimpleName(), ClassSource.from(classs));

        engineDescriptor.attach(this);

        if (specParser != null) {
            walkTestsGroupTree(specParser.testsPlan(), this);
        }
    }

    private void walkTestsGroupTree(TreeNode<TestsGroup> testsGroupTreeNode, SpecnazClassOrGroupDescriptor parent) {
        SpecnazGroupDescriptor specnazGroupDescriptor = new SpecnazGroupDescriptor(parent, testsGroupTreeNode);
        for (TreeNode<TestsGroup> childTestsGroupTreeNode : testsGroupTreeNode.children()) {
            walkTestsGroupTree(childTestsGroupTreeNode, specnazGroupDescriptor);
        }
    }

    @Override
    public void addChild(TestDescriptor child) {
        throw new UnsupportedOperationException("addChild() is not supported for SpecnazClassDescriptor");
    }
}
