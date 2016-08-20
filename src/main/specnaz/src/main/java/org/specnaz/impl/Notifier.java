package org.specnaz.impl;

public interface Notifier {
    void started(SingleTestCase testCase);

    void passed(SingleTestCase testCase);

    void failed(Throwable e);
}
