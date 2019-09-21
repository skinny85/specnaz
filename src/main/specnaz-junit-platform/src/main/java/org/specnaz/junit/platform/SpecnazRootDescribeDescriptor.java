package org.specnaz.junit.platform;

import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;
import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.SpecParser;
import org.specnaz.impl.TestsGroup;
import org.specnaz.impl.TreeNode;

public final class SpecnazRootDescribeDescriptor extends AbstractTestDescriptor {
    protected SpecnazRootDescribeDescriptor(SpecnazClassDescriptor classDescriptor, SpecParser specParser) {
        super(classDescriptor.getUniqueId().append("group", specParser.name()), specParser.name());

        classDescriptor.attach(this);

        TreeNode<TestsGroup> testsPlan = specParser.testsPlan();
        for (SingleTestCase testCase : testsPlan.value.testCases) {
            new SingleTestCaseDescriptor(this, testCase);
        }
    }

    void attach(SingleTestCaseDescriptor singleTestCaseDescriptor) {
        super.addChild(singleTestCaseDescriptor);
    }

    public void execute(EngineExecutionListener listener) {
        listener.executionStarted(this);

        for (SingleTestCaseDescriptor singleTestCaseDescriptor : singleTestCaseDescriptors()) {
            singleTestCaseDescriptor.execute(listener);
        }

        listener.executionFinished(this, TestExecutionResult.successful());
    }

    @Override
    public void addChild(TestDescriptor child) {
        throw new UnsupportedOperationException("addChild() is not supported for SpecnazRootDescribeDescriptor");
    }

    private Iterable<SingleTestCaseDescriptor> singleTestCaseDescriptors() {
        // safe because we ban children other than SingleTestCaseDescriptor by overriding addChild()
        return (Iterable<SingleTestCaseDescriptor>) getChildren();
    }

    @Override
    public Type getType() {
        return Type.CONTAINER;
    }
}
