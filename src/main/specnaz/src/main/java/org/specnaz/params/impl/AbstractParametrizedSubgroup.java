package org.specnaz.params.impl;

import org.specnaz.impl.TestCaseType;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static java.lang.String.format;

public abstract class AbstractParametrizedSubgroup {
    private final String description;
    private final TestCaseType testCaseType;
    private List<List<?>> params;

    public AbstractParametrizedSubgroup(String description, TestCaseType testCaseType) {
        this.description = description;
        this.testCaseType = testCaseType;
    }

    public void complete(List<List<?>> params) {
        this.params = params;
    }

    public final Collection<ParametrizedSubgroupInstance> instances() {
        if (params == null)
            throw new RuntimeException(format(
                    "Unfinished parametrized sub-group '%s'. " +
                            "You need to call the `provided` method on the object returned from `describes`", description));

        List<ParametrizedSubgroupInstance> ret = new LinkedList<>();
        for (List<?> paramsSet : params) {
            ret.add(new ParametrizedSubgroupInstance(
                    description(paramsSet), toSpecClosure(paramsSet), testCaseType));
        }
        return ret;
    }

    protected abstract Runnable toSpecClosure(List<?> paramsSet);

    private String description(List<?> paramsSet) {
        return Conversions.formatParamsDesc(description, paramsSet);
    }
}
