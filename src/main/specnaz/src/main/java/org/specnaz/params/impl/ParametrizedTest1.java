package org.specnaz.params.impl;

import org.specnaz.impl.SinglePositiveTestCase;
import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.TestCaseType;
import org.specnaz.params.TestClosureParams1;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public final class ParametrizedTest1<T> {
    private final String description;
    private final TestClosureParams1<T> testBody;
    private List<T> params;

    public ParametrizedTest1(String description, TestClosureParams1<T> testBody) {
        this.description = description;
        this.testBody = testBody;
    }

    @SafeVarargs
    public final void complete(T... params) {
        this.params = Arrays.asList(params);
    }

    public Collection<SingleTestCase> testCases() {
        List<SingleTestCase> ret = new LinkedList<>();
        for (T param : params) {
            ret.add(new SinglePositiveTestCase(TestCaseType.REGULAR, formatDesc(description, param), () -> {
                testBody.invoke(param);
            }));
        }
        return ret;
    }

    private String formatDesc(String description, T param) {
        if (!description.contains("%1"))
            return description;

        return description.replaceAll("%1", param.toString());
    }
}
