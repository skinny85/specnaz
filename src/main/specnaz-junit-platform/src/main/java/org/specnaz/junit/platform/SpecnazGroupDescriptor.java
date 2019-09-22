package org.specnaz.junit.platform;

import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;
import org.specnaz.impl.ExecutableTestCase;
import org.specnaz.impl.TestsGroup;
import org.specnaz.impl.TestsGroupNodeExecutor;
import org.specnaz.impl.TreeNode;

public final class SpecnazGroupDescriptor extends AbstractTestDescriptor {
    private final TestsGroupNodeExecutor testsGroupNodeExecutor;

    public SpecnazGroupDescriptor(SpecnazClassDescriptor classDescriptor,
            TreeNode<TestsGroup> testsGroupTreeNode) {
        super(classDescriptor.getUniqueId().append("group", testsGroupTreeNode.value.description),
                testsGroupTreeNode.value.description);

        classDescriptor.attach(this);

        // ToDo fix the runOnlyFocusedTests parameter
        testsGroupNodeExecutor = new TestsGroupNodeExecutor(testsGroupTreeNode, false);
        for (ExecutableTestCase executableTestCase : testsGroupNodeExecutor.executableTestCases(null)) {
            new ExecutableTestCaseDescriptor(this, executableTestCase);
        }
    }

    void attach(ExecutableTestCaseDescriptor executableTestCaseDescriptor) {
        super.addChild(executableTestCaseDescriptor);
    }

    public void execute(EngineExecutionListener listener) {
        listener.executionStarted(this);

        for (ExecutableTestCaseDescriptor executableTestCaseDescriptor : executableTestCasseDescriptors()) {
            executableTestCaseDescriptor.execute(listener);
        }

        listener.executionFinished(this, TestExecutionResult.successful());
    }

    @Override
    public void addChild(TestDescriptor child) {
        throw new UnsupportedOperationException("addChild() is not supported for SpecnazGroupDescriptor");
    }

    private Iterable<ExecutableTestCaseDescriptor> executableTestCasseDescriptors() {
        // safe because we ban children other than ExecutableTestCaseDescriptor by overriding addChild()
        return (Iterable<ExecutableTestCaseDescriptor>) getChildren();
    }

    @Override
    public Type getType() {
        return Type.CONTAINER;
    }
}
