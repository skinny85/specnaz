package org.specnaz.junit.impl;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.specnaz.impl.Notifier;
import org.specnaz.impl.SingleTestCase;

import java.util.ArrayList;

import static java.lang.String.format;
import static org.junit.runner.Description.createTestDescription;

public final class JUnitNotifier implements Notifier {
    private final RunNotifier runNotifier;
    private final Description parentDescription;

    public JUnitNotifier(RunNotifier runNotifier, Description parentDescription) {
        this.runNotifier = runNotifier;
        this.parentDescription = parentDescription;
    }

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
        runNotifier.fireTestFinished(testDescription);
    }

    private int testIndex = -1;

    private Description advanceToNextTestDescription(SingleTestCase test)  {
        testIndex++;
        ArrayList<Description> children = parentDescription.getChildren();
        while (testIndex < children.size()) {
            Description description = children.get(testIndex);
            if (description.isTest())
                return description;
            else
                testIndex++;
        }
        throw new IllegalStateException(format("Could not find Description for test '%s'", test.description));
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
    public void setupFailed(Throwable e) {
        // We do nothing here, because adding a Description not present in the original
        // test plan messes up JUnit's tree view. Instead, the Executor will mark the
        // tests as failed.
    }

    @Override
    public void teardownFailed(Throwable e) {
        // In case of an 'afterAll' failure, we have no choice - the tests were already
        // marked as passed, so we cannot do anything (marking them as 'failed' after
        // does not work, JUnit just creates a new Description for it in the tree).
        // We accept that the tree will be broken in this case - hopefully, it's a rare situation.

        Description description = createTestDescription(parentDescription.getDisplayName(),
                "should not fail in an 'afterAll' method");
        parentDescription.addChild(description);
        runNotifier.fireTestStarted(description);
        runNotifier.fireTestFailure(new Failure(description, e));
    }
}
