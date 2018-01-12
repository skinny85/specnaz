package org.specnaz.impl;

public interface Notifier {
    void setupStarted();

    void setupSucceeded();

    void setupFailed(Throwable error);

    void teardownStarted();

    void teardownSucceeded();

    void teardownFailed(Throwable error);

    void started(SingleTestCase testCase);

    void passed(SingleTestCase testCase);

    void failed(SingleTestCase testCase, Throwable e);

    void skipped(SingleTestCase testCase, Throwable assumptionViolated);

    void ignored(SingleTestCase test);

    Notifier subgroup(String description);
}
