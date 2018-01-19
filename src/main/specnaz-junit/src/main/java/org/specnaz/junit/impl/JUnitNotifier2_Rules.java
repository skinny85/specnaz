package org.specnaz.junit.impl;

import org.junit.AssumptionViolatedException;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.specnaz.impl.SingleTestCase;

public final class JUnitNotifier2_Rules {
    private final RunNotifier runNotifier;
    private final TestCase2DescriptionMap testCase2DescriptionMap;

    public JUnitNotifier2_Rules(RunNotifier runNotifier, TestCase2DescriptionMap testCase2DescriptionMap) {
        this.runNotifier = runNotifier;
        this.testCase2DescriptionMap = testCase2DescriptionMap;
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
        runNotifier.fireTestFailure(new Failure(findTeardown(test), e));
    }

    private Description findDesc(SingleTestCase test) {
        return testCase2DescriptionMap.findDesc(test);
    }

    private Description findTeardown(SingleTestCase test) {
        return testCase2DescriptionMap.findTeardown(test);
    }
}
