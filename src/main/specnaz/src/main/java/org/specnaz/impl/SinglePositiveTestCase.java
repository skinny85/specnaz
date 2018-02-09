package org.specnaz.impl;

import org.specnaz.TestSettings;
import org.specnaz.utils.TestClosure;

public final class SinglePositiveTestCase extends SingleTestCase {
    private final TestClosure testBody;

    public SinglePositiveTestCase(String description, TestClosure testBody,
            TestCaseType testCaseType, TestSettings.Wrapper testSettings) {
        super(description, testBody, testCaseType, testSettings);
        this.testBody = testBody;
    }

    @Override
    public Throwable exercise() {
        return SingleTestCase.invokeCallback(testBody);
    }
}
