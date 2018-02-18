package org.specnaz.params.impl;

import org.specnaz.impl.TestCaseType;
import org.specnaz.params.RunnableParams2;

import java.util.List;

public final class ParametrizedSubgroup2<P1, P2> extends AbstractParametrizedSubgroup {
    private final RunnableParams2<P1, P2> specClosure;

    public ParametrizedSubgroup2(String description, RunnableParams2<P1, P2> specClosure,
            TestCaseType testCaseType) {
        super(description, testCaseType);
        this.specClosure = specClosure;
    }

    @Override
    protected Runnable toSpecClosure(List<?> paramsSet) {
        return () -> specClosure.invoke((P1) paramsSet.get(0), (P2) paramsSet.get(1));
    }
}
