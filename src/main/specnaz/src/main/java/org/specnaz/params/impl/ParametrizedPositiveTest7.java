package org.specnaz.params.impl;

import org.specnaz.TestSettings;
import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams7;
import org.specnaz.utils.TestClosure;

import java.util.List;

public final class ParametrizedPositiveTest7<P1, P2, P3, P4, P5, P6, P7> extends
        AbstractParametrizedPositiveTest {
    private final TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody;

    public ParametrizedPositiveTest7(TestSettings testSettings, String description,
            TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody, TestCaseType testCaseType) {
        super(testSettings, description, testCaseType);
        this.testBody = testBody;
    }

    @Override
    protected TestClosure toTestClosure(List<?> params) {
        return Conversions.toTestClosure7(testBody, params);
    }
}
