package org.specnaz.impl;

import org.specnaz.utils.TestClosure;

public final class SingleTestCase {
    public final String description;
    public final TestClosure testBody;

    public SingleTestCase(String description, TestClosure testBody) {
        this.description = "should " + description;
        this.testBody = testBody;
    }
}
