package org.specnaz.params.impl;

import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.TestCaseType;
import org.specnaz.utils.TestClosure;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractParametrizedTest {
    protected final String description;
    public final TestCaseType testCaseType;
    private List<List<?>> params;

    AbstractParametrizedTest(String description, TestCaseType testCaseType) {
        this.description = description;
        this.testCaseType = testCaseType;
    }

    public final void complete(List<List<?>> params) {
        this.params = params;
    }

    public final Collection<SingleTestCase> testCases() {
        return params.stream().map(paramsSet -> testCase(paramsSet)).collect(Collectors.toList());
    }

    protected abstract TestClosure toTestClosure(List<?> params);

    protected abstract SingleTestCase testCase(List<?> params);

    protected final String formatDesc(String description, List<?> params) {
        if (!description.contains("%"))
            return description;

        String ret = description;
        int size = params.size();
        for (int i = size; i > 0; i--) {
            ret = ret.replaceAll("%" + i, String.valueOf(params.get(i - 1)));
        }

        return ret;
    }
}
