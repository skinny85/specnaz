package org.specnaz.junit.platform.impl;

import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestExecutionResult;
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
            TreeNode<TestsGroup> testsGroupTreeNode, boolean runOnlyFocusedTests) {
        super(descriptor.getUniqueId().append("group", testsGroupTreeNode.value.description),
                testsGroupTreeNode.value.description);

        descriptor.attach(this);

        testsGroupNodeExecutor = new TestsGroupNodeExecutor(testsGroupTreeNode, runOnlyFocusedTests);
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

        // execute the beginsAll for this group
        Throwable beforeAllsError = this.testsGroupNodeExecutor.beforeAllsExecutable().execute();

        for (ExecutableTestCaseDescriptor executableTestCaseDescriptor : this.childTestCases) {
            executableTestCaseDescriptor.execute(listener, beforeAllsError);
        }

        Throwable afterAllsError = this.testsGroupNodeExecutor.afterAllsExecutable().execute();

        for (SpecnazGroupDescriptor groupDescriptor : groupDescriptors()) {
            groupDescriptor.execute(listener);
        }

        Throwable reportedError = beforeAllsError == null
                ? afterAllsError
                : beforeAllsError;

        listener.executionFinished(this, reportedError == null
                ? TestExecutionResult.successful()
                : TestExecutionResult.failed(reportedError));
    }

    @Override
    public void addChild(TestDescriptor child) {
        throw new UnsupportedOperationException("addChild() is not supported for SpecnazGroupDescriptor");
    }
}
