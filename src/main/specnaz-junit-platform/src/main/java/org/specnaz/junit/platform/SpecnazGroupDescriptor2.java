package org.specnaz.junit.platform;

import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;
import org.junit.platform.engine.support.hierarchical.Node;
import org.specnaz.impl.ExecutableTestCase;
import org.specnaz.impl.TestsGroup;
import org.specnaz.impl.TestsGroupNodeExecutor;
import org.specnaz.impl.TreeNode;

public final class SpecnazGroupDescriptor2 extends AbstractTestDescriptor
        implements Node<SpecnazEngineExecutionContext> {
    private final TestsGroupNodeExecutor testsGroupNodeExecutor;

    public SpecnazGroupDescriptor2(SpecnazClassDescriptor2 classDescriptor,
            TreeNode<TestsGroup> testsGroupTreeNode) {
        super(classDescriptor.getUniqueId().append("group", testsGroupTreeNode.value.description),
                testsGroupTreeNode.value.description);

        classDescriptor.attach(this);

        // ToDo fix the runOnlyFocusedTests parameter
        testsGroupNodeExecutor = new TestsGroupNodeExecutor(testsGroupTreeNode, false);
        for (ExecutableTestCase executableTestCase : testsGroupNodeExecutor.executableTestCases(null)) {
            new ExecutableTestCaseDescriptor2(this, executableTestCase);
        }
    }

    void attach(ExecutableTestCaseDescriptor2 executableTestCaseDescriptor) {
        super.addChild(executableTestCaseDescriptor);
    }

    @Override
    public void addChild(TestDescriptor child) {
        throw new UnsupportedOperationException("addChild() is not supported for SpecnazGroupDescriptor");
    }

    @Override
    public Type getType() {
        return Type.CONTAINER;
    }
}
