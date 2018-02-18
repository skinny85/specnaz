package org.specnaz.params.impl;

import org.specnaz.impl.TestCaseType;
import org.specnaz.params.RunnableParams1;

import java.util.List;

public class ParametrizedSubgroup1<P> extends AbstractParametrizedSubgroup {
    private final RunnableParams1<P> specClosure;

    public ParametrizedSubgroup1(String description, RunnableParams1<P> specClosure, TestCaseType testCaseType) {
        super(description, testCaseType);
        this.specClosure = specClosure;
    }

    @Override
    protected Runnable toSpecClosure(List<?> paramsSet) {
        return () -> specClosure.invoke((P) paramsSet.get(0));
    }
}
