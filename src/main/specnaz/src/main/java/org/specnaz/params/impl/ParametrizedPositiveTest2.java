package org.specnaz.params.impl;

import org.specnaz.TestSettings;
import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams2;
import org.specnaz.utils.TestClosure;

import java.util.List;

public final class ParametrizedPositiveTest2<P1, P2> extends
        AbstractParametrizedPositiveTest {
    private final TestClosureParams2<P1, P2> testBody;

    public ParametrizedPositiveTest2(TestSettings testSettings,
            String description, TestClosureParams2<P1, P2> testBody, TestCaseType testCaseType) {
        super(testSettings, description, testCaseType);
        this.testBody = testBody;
    }

    @Override
    protected TestClosure toTestClosure(List<?> params) {
        return Conversions.toTestClosure2(testBody, params);
    }
}
