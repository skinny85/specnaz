package org.specnaz.impl;

import java.util.Collection;

public final class ExecutableTestGroup {
    private final TestsGroupNodeRunner2_Rules testsGroupNodeRunner;

    public ExecutableTestGroup(TestsGroupNodeRunner2_Rules testsGroupNodeRunner) {
        this.testsGroupNodeRunner = testsGroupNodeRunner;
    }

    public Executable beforeAllsExecutable() {
        return testsGroupNodeRunner.beforeAllsExecutable();
    }

    public Collection<ExecutableTestCase> executableTestCases(Throwable beforeAllsError) {
        return testsGroupNodeRunner.executableTestCases(beforeAllsError);
    }

    public Executable afterAllsExecutable() {
        return testsGroupNodeRunner.afterAllsExecutable();
    }
}
