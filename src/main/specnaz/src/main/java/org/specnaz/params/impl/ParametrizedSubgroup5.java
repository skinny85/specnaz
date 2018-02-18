package org.specnaz.params.impl;

import org.specnaz.impl.TestCaseType;
import org.specnaz.params.RunnableParams5;

import java.util.List;

public final class ParametrizedSubgroup5<P1, P2, P3, P4, P5> extends AbstractParametrizedSubgroup {
    private final RunnableParams5<P1, P2, P3, P4, P5> specClosure;

    public ParametrizedSubgroup5(String description,
            RunnableParams5<P1, P2, P3, P4, P5> specClosure, TestCaseType testCaseType) {
        super(description, testCaseType);
        this.specClosure = specClosure;
    }

    @Override
    protected Runnable toSpecClosure(List<?> paramsSet) {
        return () -> specClosure.invoke((P1) paramsSet.get(0), (P2) paramsSet.get(1),
                (P3) paramsSet.get(2), (P4) paramsSet.get(3), (P5) paramsSet.get(4));
    }
}
