package org.specnaz.junit.impl;

import org.junit.AssumptionViolatedException;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.specnaz.impl.SingleTestCase;

public final class JUnitNotifier {
    private final RunNotifier runNotifier;
    private final TestCases2DescriptionsMap testCases2DescriptionsMap;

    public JUnitNotifier(RunNotifier runNotifier, TestCases2DescriptionsMap testCases2DescriptionsMap) {
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
        Description teardownDescription = findTeardown(test);
        if (teardownDescription != null) {
            runNotifier.fireTestStarted(teardownDescription);
        }
    }

    public void teardownSucceeded(SingleTestCase test) {
        Description teardownDescription = findTeardown(test);
        if (teardownDescription != null) {
            runNotifier.fireTestFinished(teardownDescription);
        }
    }

    public void teardownFailed(SingleTestCase test, Throwable e) {
        Description teardownDescription = findTeardown(test);
        if (teardownDescription != null) {
            runNotifier.fireTestFailure(new Failure(teardownDescription, e));
            runNotifier.fireTestFinished(teardownDescription);
        }
    }

    private Description findDesc(SingleTestCase test) {
        return testCases2DescriptionsMap.findDesc(test);
    }

    private Description findTeardown(SingleTestCase test) {
        return testCases2DescriptionsMap.findTeardown(test);
    }
}
