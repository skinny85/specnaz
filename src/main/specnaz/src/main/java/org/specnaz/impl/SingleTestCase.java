package org.specnaz.impl;

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

    SingleTestCase(TestCaseType type, String description) {
        this.type = type;
        this.description = description;
    }

    public abstract SingleTestCase type(TestCaseType type);

    public abstract Throwable exercise();
}
