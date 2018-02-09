package org.specnaz.params.impl;

import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams1;
import org.specnaz.utils.TestClosure;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractParametrizedTest1<P> {
    protected final String description;
    protected final TestClosureParams1<P> testBody;
    protected final TestCaseType testCaseType;
    private List<P> params;

    AbstractParametrizedTest1(String description, TestClosureParams1<P> testBody, TestCaseType testCaseType) {
        this.testBody = testBody;
        this.description = description;
        this.testCaseType = testCaseType;
    }

    @SafeVarargs
    public final void complete(P... params) {
        this.params = Arrays.asList(params);
    }

    public Collection<SingleTestCase> testCases() {
        List<SingleTestCase> ret = new LinkedList<>();
        for (P param : params) {
            ret.add(testCase(param));
        }
        return ret;
    }

    protected abstract SingleTestCase testCase(P param);

    protected final String formatDesc(String description, Object param) {
        if (!description.contains("%1"))
            return description;

        return description.replaceAll("%1", param.toString());
    }

    protected final TestClosure toTestClosure(P param) {
        return () -> testBody.invoke(param);
    }
}
