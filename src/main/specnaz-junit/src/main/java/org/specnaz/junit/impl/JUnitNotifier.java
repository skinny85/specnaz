package org.specnaz.junit.impl;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.specnaz.impl.Notifier;
import org.specnaz.impl.SingleTestCase;

import java.util.ArrayList;

import static java.lang.String.format;

public final class JUnitNotifier implements Notifier {
    private final RunNotifier runNotifier;
    private final Description parentDescription;

    public JUnitNotifier(RunNotifier runNotifier, Description parentDescription) {
        this.runNotifier = runNotifier;
        this.parentDescription = parentDescription;
    }

    /*
     * This class is a work of art.
     *
     * Because of the weird way JUnit is structured,
     * there is no easy way to associate a description with a test.
     * So, in this class, we take advantage of knowing in
     * which order the tests are executed, and traverse the
     * description tree in lock-step with the test execution.
     *
     * This is the ugliest code I've ever written, but I've ran
     * out of ideas on how to handle this problem differently,
     * and it works. I'd love to hear ideas on how to improve it.
     */

    @Override
    public void started(SingleTestCase test) {
        runNotifier.fireTestStarted(advanceToNextTestDescription(test));
    }

    @Override
    public void passed(SingleTestCase test) {
        runNotifier.fireTestFinished(currentTestDescription());
    }

    @Override
    public void failed(SingleTestCase test, Throwable error) {
        Description testDescription = currentTestDescription();
        runNotifier.fireTestFailure(new Failure(testDescription, error));
        runNotifier.fireTestFinished(testDescription);
    }

    @Override
    public void ignored(SingleTestCase test) {
        Description testDescription = advanceToNextTestDescription(test);
        runNotifier.fireTestIgnored(testDescription);
    }

    private int testIndex = -1;

    private Description advanceToNextTestDescription(SingleTestCase test)  {
        return advanceToNextTestDescription(test.description);
    }

    private Description advanceToNextTestDescription(String message) {
        testIndex++;
        ArrayList<Description> children = parentDescription.getChildren();
        while (testIndex < children.size()) {
            Description description = children.get(testIndex);
            if (description.isTest())
                return description;
            else
                testIndex++;
        }
        throw new IllegalStateException(format("Could not find Description for test '%s'", message));
    }

    private Description currentTestDescription() {
        return parentDescription.getChildren().get(testIndex);
    }

    @Override
    public Notifier subgroup(String description) {
        return new JUnitNotifier(runNotifier,
                advanceToNextSuiteDescription(description));
    }

    private int suiteIndex = -1;

    private Description advanceToNextSuiteDescription(String groupDescription)  {
        suiteIndex++;
        ArrayList<Description> children = parentDescription.getChildren();
        while (suiteIndex < children.size()) {
            Description description = children.get(suiteIndex);
            if (description.isSuite())
                return description;
            else
                suiteIndex++;
        }
        throw new IllegalStateException(format("Could not find Description for group '%s'", groupDescription));
    }

    @Override
    public void setupStarted() {
    }

    @Override
    public void setupSucceeded() {
    }

    @Override
    public void setupFailed(Throwable e) {
        // We do nothing here, because adding a Description not present in the original
        // test plan messes up JUnit's tree view. Instead, the Executor will mark the
        // tests as failed.
    }

    @Override
    public void teardownStarted() {
        runNotifier.fireTestStarted(advanceToNextTestDescription("teardown"));
    }

    @Override
    public void teardownSucceeded() {
        runNotifier.fireTestFinished(currentTestDescription());
    }

    @Override
    public void teardownFailed(Throwable e) {
        runNotifier.fireTestFailure(new Failure(currentTestDescription(), e));
    }
}
