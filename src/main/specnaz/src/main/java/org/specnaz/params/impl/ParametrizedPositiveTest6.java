package org.specnaz.params.impl;

import org.specnaz.TestSettings;
import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams6;
import org.specnaz.utils.TestClosure;

import java.util.List;

public final class ParametrizedPositiveTest6<P1, P2, P3, P4, P5, P6> extends
        AbstractParametrizedPositiveTest {
    private final TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody;

    public ParametrizedPositiveTest6(TestSettings testSettings, String description,
            TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody, TestCaseType testCaseType) {
        super(testSettings, description, testCaseType);
        this.testBody = testBody;
    }

    @Override
    protected TestClosure toTestClosure(List<?> params) {
        return Conversions.toTestClosure6(testBody, params);
    }
}
