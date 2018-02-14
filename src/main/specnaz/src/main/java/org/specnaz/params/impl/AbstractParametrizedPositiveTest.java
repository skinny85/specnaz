package org.specnaz.params.impl;

import org.specnaz.TestSettings;
import org.specnaz.impl.SinglePositiveTestCase;
import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.TestCaseType;

import java.util.List;

public abstract class AbstractParametrizedPositiveTest extends AbstractParametrizedTest {
    public final TestSettings testSettings;

    AbstractParametrizedPositiveTest(TestSettings testSettings, String description, TestCaseType testCaseType) {
        super(description, testCaseType);
        this.testSettings = testSettings;
    }

    @Override
    protected SingleTestCase testCase(List<?> params) {
        return new SinglePositiveTestCase(testSettings,
                formatDesc(params), toTestClosure(params), testCaseType);
    }
}
