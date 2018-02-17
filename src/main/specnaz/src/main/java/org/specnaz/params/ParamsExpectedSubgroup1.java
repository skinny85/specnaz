package org.specnaz.params;

import org.specnaz.impl.TestCaseType;
import org.specnaz.impl.TestsTreeCoreDslBuilder;

public final class ParamsExpectedSubgroup1<P> {
    private final String description;
    private final ConsumerParams1<P> specClosure;
    private final TestCaseType testCaseType;
    private final TestsTreeCoreDslBuilder testsTreeCoreDslBuilder;

    public ParamsExpectedSubgroup1(String description, ConsumerParams1<P> specClosure, TestCaseType testCaseType,
            TestsTreeCoreDslBuilder testsTreeCoreDslBuilder) {
        this.description = description;
        this.specClosure = specClosure;
        this.testCaseType = testCaseType;
        this.testsTreeCoreDslBuilder = testsTreeCoreDslBuilder;
    }

    @SafeVarargs
    public final void provided(P... params) {
        for (P param : params) {
            testsTreeCoreDslBuilder.handleSubSpecification(description, () -> specClosure.invoke(param), testCaseType);
        }
    }
}
