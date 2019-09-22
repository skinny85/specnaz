package org.specnaz.junit.platform;

import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;
import org.specnaz.impl.ExecutableTestCase;

public class ExecutableTestCaseDescriptor extends AbstractTestDescriptor {
    public ExecutableTestCaseDescriptor(SpecnazGroupDescriptor groupDescriptor, ExecutableTestCase executableTestCase) {
        super(groupDescriptor.getUniqueId().append("test", executableTestCase.testCase.description),
                executableTestCase.testCase.description);

        groupDescriptor.attach(this);
    }

    @Override
    public Type getType() {
        return Type.TEST;
    }

    public void execute(EngineExecutionListener listener) {
        listener.executionStarted(this);
        listener.executionFinished(this, TestExecutionResult.successful());
    }
}
