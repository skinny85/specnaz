package org.specnaz.junit.platform;

import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;
import org.junit.platform.engine.support.hierarchical.Node;
import org.specnaz.impl.ExecutableTestCase;

public final class ExecutableTestCaseDescriptor2 extends AbstractTestDescriptor
        implements Node<SpecnazEngineExecutionContext> {
    private final ExecutableTestCase executableTestCase;

    public ExecutableTestCaseDescriptor2(SpecnazGroupDescriptor2 groupDescriptor, ExecutableTestCase executableTestCase) {
        super(groupDescriptor.getUniqueId().append("test", executableTestCase.testCase.description),
                executableTestCase.testCase.description);

        this.executableTestCase = executableTestCase;

        groupDescriptor.attach(this);
    }

    @Override
    public SpecnazEngineExecutionContext execute(SpecnazEngineExecutionContext context,
            DynamicTestExecutor dynamicTestExecutor) throws Exception {
        Throwable throwable = executableTestCase.execute();

        if (throwable != null) {
            if (throwable instanceof Exception) {
                throw (Exception) throwable;
            } else if (throwable instanceof Error) {
                throw (Error) throwable;
            } else {
                throw new Exception(throwable);
            }
        } else {
            return context;
        }
    }

    @Override
    public Type getType() {
        return Type.TEST;
    }
}
