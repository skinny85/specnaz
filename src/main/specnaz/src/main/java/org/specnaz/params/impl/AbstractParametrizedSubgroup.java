package org.specnaz.params.impl;

import org.specnaz.impl.TestCaseType;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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

    public Collection<ParametrizedSubgroupInstance> instances() {
        // ToDO if params == null throw here

        List<ParametrizedSubgroupInstance> ret = new LinkedList<>();
        for (List<?> paramsSet : params) {
            ret.add(new ParametrizedSubgroupInstance(
                    description(paramsSet), toSpecClosure(paramsSet), testCaseType));
        }
        return ret;
    }

    protected abstract Runnable toSpecClosure(List<?> paramsSet);

    private String description(List<?> paramsSet) {
        // ToDo extract this duplication

        if (!description.contains("%"))
            return description;

        String ret = description;
        int size = paramsSet.size();
        // we do it in reverse, so that '%10' gets processed
        // before '%1' - not that we support '%10' now anyway,
        // but it's probably better to be aware of this issue
        // sooner rather than later :)
        for (int i = size; i > 0; i--) {
            ret = ret.replaceAll("%" + i, String.valueOf(paramsSet.get(i - 1)));
        }

        return ret;
    }
}
