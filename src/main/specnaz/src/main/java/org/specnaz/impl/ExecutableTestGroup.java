package org.specnaz.impl;

import java.util.Collection;
import java.util.stream.Collectors;

public final class ExecutableTestGroup {
    private final TestsGroupNodeRunner2_Rules testsGroupNodeRunner;
    public final Notifier notifier;

    public ExecutableTestGroup(TestsGroupNodeRunner2_Rules testsGroupNodeRunner, Notifier notifier) {
        this.testsGroupNodeRunner = testsGroupNodeRunner;
        this.notifier = notifier;
    }

    public Executable beforeAllsClosure() {
        return testsGroupNodeRunner.beforeAllsCount() == 0
                ? null
                : testsGroupNodeRunner::invokeBeforeAlls;
    }

    public Collection<ExecutionClosure> individualTestsClosures(Throwable beforeAllsError) {
        return testsGroupNodeRunner.testCases().stream().map(testCase -> testsGroupNodeRunner.shouldIgnoreTest(testCase)
                ? new ExecutionClosure(testCase)
                : new ExecutionClosure(testCase, () -> testsGroupNodeRunner.runSingleTestCase(testCase, beforeAllsError))
        ).collect(Collectors.toList());
    }

    public Executable afterAllsClosure() {
        return testsGroupNodeRunner.afterAllsCount() == 0
                ? null
                : testsGroupNodeRunner::invokeAfterAlls;
    }
}
