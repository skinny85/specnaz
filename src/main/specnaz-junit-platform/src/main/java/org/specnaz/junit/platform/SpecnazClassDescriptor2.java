package org.specnaz.junit.platform;

import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;
import org.junit.platform.engine.support.descriptor.ClassSource;
import org.junit.platform.engine.support.hierarchical.Node;
import org.specnaz.impl.SpecParser;
import org.specnaz.impl.TestsGroup;
import org.specnaz.impl.TreeNode;

public final class SpecnazClassDescriptor2 extends AbstractTestDescriptor
        implements Node<SpecnazEngineExecutionContext> {
    public SpecnazClassDescriptor2(SpecnazEngineDescriptor2 engineDescriptor, Class<?> classs, SpecParser specParser) {
        super(engineDescriptor.getUniqueId().append("class", classs.getCanonicalName()),
                classs.getSimpleName(), ClassSource.from(classs));

        engineDescriptor.attach(this);

        if (specParser != null) {
            TreeNode<TestsGroup> testsGroupTreeNode = specParser.testsPlan();
            new SpecnazGroupDescriptor2(this, testsGroupTreeNode);
        }
    }

    void attach(SpecnazGroupDescriptor2 groupDescriptor) {
        super.addChild(groupDescriptor);
    }

    @Override
    public void addChild(TestDescriptor child) {
        throw new UnsupportedOperationException("addChild() is not supported for SpecnazClassDescriptor");
    }

    @Override
    public Type getType() {
        return Type.CONTAINER;
    }
}
