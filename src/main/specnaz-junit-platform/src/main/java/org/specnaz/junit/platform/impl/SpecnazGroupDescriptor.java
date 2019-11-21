package org.specnaz.junit.platform.impl;

import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;
import org.specnaz.impl.ExecutableTestCase;
import org.specnaz.impl.TestsGroup;
import org.specnaz.impl.TestsGroupNodeExecutor;
import org.specnaz.impl.TreeNode;

import java.util.LinkedList;
import java.util.List;

public final class SpecnazGroupDescriptor extends SpecnazClassOrGroupDescriptor {
    private final TestsGroupNodeExecutor testsGroupNodeExecutor;
    private final List<ExecutableTestCaseDescriptor> childTestCases = new LinkedList<>();

    public SpecnazGroupDescriptor(SpecnazClassOrGroupDescriptor descriptor,
            TreeNode<TestsGroup> testsGroupTreeNode) {
        super(descriptor.getUniqueId().append("group", testsGroupTreeNode.value.description),
                testsGroupTreeNode.value.description);

        descriptor.attach(this);

        // ToDo fix the runOnlyFocusedTests parameter
        testsGroupNodeExecutor = new TestsGroupNodeExecutor(testsGroupTreeNode, false);
        for (ExecutableTestCase executableTestCase : testsGroupNodeExecutor.executableTestCases(null)) {
            new ExecutableTestCaseDescriptor(this, executableTestCase);
        }
    }

    void attach(ExecutableTestCaseDescriptor executableTestCaseDescriptor) {
        super.addChild(executableTestCaseDescriptor);
        this.childTestCases.add(executableTestCaseDescriptor);
    }

    public void execute(EngineExecutionListener listener) {
        listener.executionStarted(this);

        for (ExecutableTestCaseDescriptor executableTestCaseDescriptor : this.childTestCases) {
            executableTestCaseDescriptor.execute(listener);
        }

        for (SpecnazGroupDescriptor groupDescriptor : groupDescriptors()) {
            groupDescriptor.execute(listener);
        }

        listener.executionFinished(this, TestExecutionResult.successful());
    }

    @Override
    public void addChild(TestDescriptor child) {
        throw new UnsupportedOperationException("addChild() is not supported for SpecnazGroupDescriptor");
    }
}
