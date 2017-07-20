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

    private final String description;

    SingleTestCase(String description) {
        this.description = description;
    }

    public abstract Throwable exercise();

    public String description() {
        return description;
    }
}
