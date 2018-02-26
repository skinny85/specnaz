package org.specnaz.params.impl;

import org.specnaz.TestSettings;
import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams8;
import org.specnaz.utils.TestClosure;

import java.util.List;

public final class ParametrizedPositiveTest8<P1, P2, P3, P4, P5, P6, P7, P8> extends
        AbstractParametrizedPositiveTest {
    private final TestClosureParams8<P1, P2, P3, P4, P5, P6, P7, P8> testBody;

    public ParametrizedPositiveTest8(TestSettings testSettings, String description,
            TestClosureParams8<P1, P2, P3, P4, P5, P6, P7, P8> testBody, TestCaseType testCaseType) {
        super(testSettings, description, testCaseType);
        this.testBody = testBody;
    }

    @Override
    protected TestClosure toTestClosure(List<?> params) {
        return Conversions.toTestClosure8(testBody, params);
    }
}
