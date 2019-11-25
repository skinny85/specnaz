package org.specnaz.junit.platform.impl;

import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;
import org.specnaz.impl.ExecutableTestCase;

public final class ExecutableTestCaseDescriptor extends AbstractTestDescriptor {
    private final ExecutableTestCase executableTestCase;

    public ExecutableTestCaseDescriptor(SpecnazGroupDescriptor groupDescriptor, ExecutableTestCase executableTestCase) {
        super(groupDescriptor.getUniqueId().append("test", executableTestCase.testCase.description),
                executableTestCase.testCase.description);

        this.executableTestCase = executableTestCase;

        groupDescriptor.attach(this);
    }

    @Override
    public Type getType() {
        return Type.TEST;
    }

    public void execute(EngineExecutionListener listener, Throwable setupError) {
        if (this.executableTestCase.isIgnored()) {
            listener.executionSkipped(this, "test ignored");
        } else {
            listener.executionStarted(this);

            Throwable result = setupError == null
                    ? executableTestCase.execute()
                    : setupError;

            listener.executionFinished(this, result == null
                    ? TestExecutionResult.successful()
                    : TestExecutionResult.failed(result));
        }
    }
}
