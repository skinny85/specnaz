package org.specnaz.junit.impl;

import org.junit.AssumptionViolatedException;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.specnaz.impl.SingleTestCase;

public final class JUnitNotifier2_Rules {
    private final RunNotifier runNotifier;
    private final TestCases2DescriptionsMap testCases2DescriptionsMap;

    public JUnitNotifier2_Rules(RunNotifier runNotifier, TestCases2DescriptionsMap testCases2DescriptionsMap) {
        this.runNotifier = runNotifier;
        this.testCases2DescriptionsMap = testCases2DescriptionsMap;
    }

    public void started(SingleTestCase test) {
        runNotifier.fireTestStarted(findDesc(test));
    }

    public void passed(SingleTestCase test) {
        runNotifier.fireTestFinished(findDesc(test));
    }

    public void failed(SingleTestCase test, Throwable error) {
        Description testDescription = findDesc(test);
        runNotifier.fireTestFailure(new Failure(testDescription, error));
        runNotifier.fireTestFinished(testDescription);
    }

    public void skipped(SingleTestCase test, AssumptionViolatedException assumptionViolated) {
        Description testDescription = findDesc(test);
        runNotifier.fireTestAssumptionFailed(new Failure(testDescription, assumptionViolated));
        runNotifier.fireTestFinished(testDescription);
    }

    public void ignored(SingleTestCase test) {
        runNotifier.fireTestIgnored(findDesc(test));
    }

    public void teardownStarted(SingleTestCase test) {
        runNotifier.fireTestStarted(findTeardown(test));
    }

    public void teardownSucceeded(SingleTestCase test) {
        runNotifier.fireTestFinished(findTeardown(test));
    }

    public void teardownFailed(SingleTestCase test, Throwable e) {
        Description testDescription = findTeardown(test);
        runNotifier.fireTestFailure(new Failure(testDescription, e));
        runNotifier.fireTestFinished(testDescription);
    }

    private Description findDesc(SingleTestCase test) {
        return testCases2DescriptionsMap.findDesc(test);
    }

    private Description findTeardown(SingleTestCase test) {
        return testCases2DescriptionsMap.findTeardown(test);
    }
}
