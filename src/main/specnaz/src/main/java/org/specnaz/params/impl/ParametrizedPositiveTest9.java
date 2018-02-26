package org.specnaz.params.impl;

import org.specnaz.TestSettings;
import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams9;
import org.specnaz.utils.TestClosure;

import java.util.List;

public final class ParametrizedPositiveTest9<P1, P2, P3, P4, P5, P6, P7, P8, P9> extends
        AbstractParametrizedPositiveTest {
    private final TestClosureParams9<P1, P2, P3, P4, P5, P6, P7, P8, P9> testBody;

    public ParametrizedPositiveTest9(TestSettings testSettings, String description,
            TestClosureParams9<P1, P2, P3, P4, P5, P6, P7, P8, P9> testBody, TestCaseType testCaseType) {
        super(testSettings, description, testCaseType);
        this.testBody = testBody;
    }

    @Override
    protected TestClosure toTestClosure(List<?> params) {
        return Conversions.toTestClosure9(testBody, params);
    }
}
