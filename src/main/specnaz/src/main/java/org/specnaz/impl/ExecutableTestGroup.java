package org.specnaz.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class ExecutableTestGroup {
    private final TestsGroupNodeRunner2_Rules testsGroupNodeRunner;

    public ExecutableTestGroup(TestsGroupNodeRunner2_Rules testsGroupNodeRunner) {
        this.testsGroupNodeRunner = testsGroupNodeRunner;
    }

    public ExecutionClosure beforeAllsClosure() {
        return testsGroupNodeRunner.shouldSkipAllsFixtures()
                ? null
                : () -> {
            Throwable throwable = testsGroupNodeRunner.invokeBeforeAlls();
            if (throwable != null)
                throw throwable;
        };
    }

    public Collection<ExecutionClosure> individualTestsClosures(Throwable beforeAllsError) {
        List<ExecutionClosure> ret = new ArrayList<>(testsGroupNodeRunner.testCases().size());
        for (SingleTestCase testCase : testsGroupNodeRunner.testCases()) {
            ret.add(testsGroupNodeRunner.shouldIgnoreTest(testCase)
                    ? null
                    : () -> {
                Throwable throwable = testsGroupNodeRunner.runSingleTestCase2(testCase, beforeAllsError);
                if (throwable != null)
                    throw throwable;
            });
        }

        return ret;
    }
}
