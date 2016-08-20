package org.specnaz.impl;

public interface Notifier {
    void setupFailed(Throwable error);

    void teardownFailed(Throwable error);

    void started(SingleTestCase testCase);

    void passed(SingleTestCase testCase);

    void failed(SingleTestCase testCase, Throwable e);

    void ignored(SingleTestCase test);

    Notifier subgroup(String description);
}
