package org.specnaz.impl;

import java.util.Collection;

public final class ExecutableTestGroup {
    private final TestsGroupNodeRunner2_Rules testsGroupNodeRunner;

    public ExecutableTestGroup(TestsGroupNodeRunner2_Rules testsGroupNodeRunner) {
        this.testsGroupNodeRunner = testsGroupNodeRunner;
    }

    public Executable beforeAllsClosure() {
        return testsGroupNodeRunner.beforeAllsCount() == 0
                ? null
                : testsGroupNodeRunner::invokeBeforeAlls;
    }

    public Collection<ExecutableTestCase> executableTestCases(Throwable beforeAllsError) {
        return testsGroupNodeRunner.executableTestCases(beforeAllsError);
    }

    public Executable afterAllsClosure() {
        return testsGroupNodeRunner.afterAllsCount() == 0
                ? null
                : testsGroupNodeRunner::invokeAfterAlls;
    }
}
