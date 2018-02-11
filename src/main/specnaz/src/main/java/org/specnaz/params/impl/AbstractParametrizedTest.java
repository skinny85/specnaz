package org.specnaz.params.impl;

import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.TestCaseType;
import org.specnaz.utils.TestClosure;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

public abstract class AbstractParametrizedTest {
    public final TestCaseType testCaseType;
    private final String description;
    private List<List<?>> params;

    AbstractParametrizedTest(String description, TestCaseType testCaseType) {
        this.description = description;
        this.testCaseType = testCaseType;
    }

    public final void complete(List<List<?>> params) {
        this.params = params;
    }

    public final Collection<SingleTestCase> testCases() {
        if (params == null)
            throw new RuntimeException(format(
                    "Unfinished parametrized test '%s'. " +
                            "You need to call the `provided` method on the object returned from `should`", description));

        return params.stream().map(paramsSet -> testCase(paramsSet)).collect(Collectors.toList());
    }

    protected abstract TestClosure toTestClosure(List<?> params);

    protected abstract SingleTestCase testCase(List<?> params);

    protected final String formatDesc(List<?> params) {
        if (!description.contains("%"))
            return description;

        String ret = description;
        int size = params.size();
        // we do it in reverse, so that '%10' gets processed
        // before '%1' - not that we support '%10' now anyway,
        // but it's probably better to be aware of this issue
        // sooner rather than later :)
        for (int i = size; i > 0; i--) {
            ret = ret.replaceAll("%" + i, String.valueOf(params.get(i - 1)));
        }

        return ret;
    }
}
