package org.specnaz.junit.platform;

import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;
import org.specnaz.impl.SingleTestCase;

public class SingleTestCaseDescriptor extends AbstractTestDescriptor {
    public SingleTestCaseDescriptor(SpecnazRootDescribeDescriptor rootDescribeDescriptor, SingleTestCase testCase) {
        super(rootDescribeDescriptor.getUniqueId().append("test", testCase.description), testCase.description);

        rootDescribeDescriptor.attach(this);
    }

    public void execute(EngineExecutionListener listener) {
        listener.executionStarted(this);
        listener.executionFinished(this, TestExecutionResult.successful());
    }

    @Override
    public Type getType() {
        return Type.TEST;
    }
}
