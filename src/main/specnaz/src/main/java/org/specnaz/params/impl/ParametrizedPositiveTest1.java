package org.specnaz.params.impl;

import org.specnaz.TestSettings;
import org.specnaz.impl.SinglePositiveTestCase;
import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams1;
import org.specnaz.utils.TestClosure;

import java.util.List;

public final class ParametrizedPositiveTest1<P> extends AbstractParametrizedPositiveTest {
    private final TestClosureParams1<P> testBody;

    public ParametrizedPositiveTest1(TestSettings testSettings,
            String description, TestClosureParams1<P> testBody, TestCaseType testCaseType) {
        super(testSettings, description, testCaseType);
        this.testBody = testBody;
    }

    @Override
    protected TestClosure toTestClosure(List<?> params) {
        return () -> testBody.invoke((P) params.get(0));
    }
}
