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

    public final TestCaseType type;
    public final String description;
    private final TestSettings.Wrapper testSettings;

    SingleTestCase(TestCaseType type, String description) {
        this.type = type;
        this.description = description;
        this.testSettings = new TestSettings.Wrapper(new TestSettings());
    }

    public abstract SingleTestCase type(TestCaseType type);

    public abstract Throwable exercise();

    public TestSettings.Wrapper testSettings() {
        return testSettings;
    }
}
