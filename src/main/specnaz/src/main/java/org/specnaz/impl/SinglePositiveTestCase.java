package org.specnaz.impl;

import org.specnaz.utils.TestClosure;

public final class SinglePositiveTestCase extends SingleTestCase {
    private final TestClosure testBody;

    public SinglePositiveTestCase(TestCaseType type, String description, TestClosure testBody) {
        super(type, description);
        this.testBody = testBody;
    }

    @Override
    public SingleTestCase type(TestCaseType type) {
        return new SinglePositiveTestCase(type, description, testBody);
    }

    @Override
    public Throwable exercise() {
        return SingleTestCase.invokeCallback(testBody);
    }
}
