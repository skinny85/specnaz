package org.specnaz.junit.impl;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.specnaz.impl.Notifier;
import org.specnaz.impl.SingleTestCase;

public final class JUnitNotifier implements Notifier {
    private final RunNotifier runNotifier;
    private final Description rootDescription;
    private int index = -1;

    public JUnitNotifier(RunNotifier runNotifier, Description rootDescription) {
        this.runNotifier = runNotifier;
        this.rootDescription = rootDescription;
    }

    @Override
    public void started(SingleTestCase testCase) {
        runNotifier.fireTestStarted(getDescription(++index));
    }

    @Override
    public void passed(SingleTestCase testCase) {
        runNotifier.fireTestFinished(getDescription(index));
    }

    @Override
    public void failed(Throwable e) {
        runNotifier.fireTestFailure(new Failure(getDescription(index), e));
        runNotifier.fireTestFinished(getDescription(index));
    }

    private Description getDescription(int index) {
        return rootDescription.getChildren().get(index);
    }
}
