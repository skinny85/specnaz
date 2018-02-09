package org.specnaz.params.impl;

import org.specnaz.TestSettings;
import org.specnaz.impl.SinglePositiveTestCase;
import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams1;

public final class ParametrizedTest1<P> extends AbstractParametrizedTest1<P> {
    private final TestSettings testSettings;

    public ParametrizedTest1(String description, TestClosureParams1<P> testBody, TestSettings testSettings) {
        super(description, testBody);
        this.testSettings = testSettings;
    }

    @Override
    protected SingleTestCase testCase(P param) {
        return new SinglePositiveTestCase(testSettings,
                formatDesc(description, param), toTestClosure(param), TestCaseType.REGULAR);
    }
}
