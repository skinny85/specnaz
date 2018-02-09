package org.specnaz.impl;

import org.specnaz.TestSettings;
import org.specnaz.utils.TestClosure;

public abstract class SingleTestCase {
    public static Throwable invokeCallback(TestClosure callback) {
        try {
            callback.invoke();
            return null;
        } catch (AssertionError | Exception e) {
            return e;
        }
    }

    public final TestSettings.Wrapper testSettings;
    public final String description;
    private final TestClosure testBody;
    public final TestCaseType type;

    SingleTestCase(TestSettings testSettings, String description, TestClosure testBody,
            TestCaseType testCaseType) {
        this.testSettings = new TestSettings.Wrapper(testSettings);
        this.description = description;
        this.testBody = testBody;
        this.type = testCaseType;
    }

    public Throwable exercise() {
        return invokeCallback(testBody);
    }
}
