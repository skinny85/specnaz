package org.specnaz.params.impl;

import org.specnaz.TestSettings;
import org.specnaz.impl.SinglePositiveTestCase;
import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams1;

public final class ParametrizedPositiveTest1<P> extends AbstractParametrizedTest1<P> {
    private final TestSettings testSettings;

    public ParametrizedPositiveTest1(TestSettings testSettings,
            String description, TestClosureParams1<P> testBody, TestCaseType testCaseType) {
        super(description, testBody, testCaseType);
        this.testSettings = testSettings;
    }

    @Override
    protected SingleTestCase testCase(P param) {
        return new SinglePositiveTestCase(testSettings,
                formatDesc(description, param), toTestClosure(param), testCaseType);
    }
}
