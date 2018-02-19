package org.specnaz.params.impl;

import org.specnaz.TestSettings;
import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams5;
import org.specnaz.utils.TestClosure;

import java.util.List;

public final class ParametrizedPositiveTest5<P1, P2, P3, P4, P5> extends
        AbstractParametrizedPositiveTest {
    private final TestClosureParams5<P1, P2, P3, P4, P5> testBody;

    public ParametrizedPositiveTest5(TestSettings testSettings, String description,
            TestClosureParams5<P1, P2, P3, P4, P5> testBody, TestCaseType testCaseType) {
        super(testSettings, description, testCaseType);
        this.testBody = testBody;
    }

    @Override
    protected TestClosure toTestClosure(List<?> params) {
        return Conversions.toTestClosure5(testBody, params);
    }
}
