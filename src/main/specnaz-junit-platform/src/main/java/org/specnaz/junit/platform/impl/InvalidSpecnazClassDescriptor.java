package org.specnaz.junit.platform.impl;

import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestExecutionResult;

final class InvalidSpecnazClassDescriptor extends SpecnazClassDescriptor {
    private final AlwaysFailTestDescriptor alwaysFailTestDescriptor;

    public InvalidSpecnazClassDescriptor(SpecnazEngineDescriptor engineDescriptor, Class<?> classs, Throwable error) {
        super(engineDescriptor, classs);

        this.alwaysFailTestDescriptor = new AlwaysFailTestDescriptor(this, error);
    }

    void attach(AlwaysFailTestDescriptor failTestDescriptor) {
        super.addChild(failTestDescriptor);
    }

    @Override
    public void execute(EngineExecutionListener listener) {
        listener.executionStarted(this);

        this.alwaysFailTestDescriptor.execute(listener);

        listener.executionFinished(this, TestExecutionResult.successful());
    }

    @Override
    public void addChild(TestDescriptor child) {
        throw new UnsupportedOperationException("addChild() is not supported for InvalidSpecnazClassDescriptor");
    }
}
