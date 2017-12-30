package org.specnaz.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class ExecutableTestGroup {
    private final TestsGroupNodeRunner2_Rules testsGroupNodeRunner;
    private final Notifier notifier;

    public ExecutableTestGroup(TestsGroupNodeRunner2_Rules testsGroupNodeRunner, Notifier notifier) {
        this.testsGroupNodeRunner = testsGroupNodeRunner;
        this.notifier = notifier;
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
                    ? () -> {
                notifier.ignored(testCase);
            }
                    : () -> {
                notifier.started(testCase);
                Throwable throwable = testsGroupNodeRunner.runSingleTestCase2(testCase, beforeAllsError);
                if (throwable != null) {
                    notifier.failed(testCase, throwable);
                    throw throwable;
                } else {
                    notifier.passed(testCase);
                }
            });
        }

        return ret;
    }
}
