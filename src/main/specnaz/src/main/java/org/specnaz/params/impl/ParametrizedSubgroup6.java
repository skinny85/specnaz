package org.specnaz.params.impl;

import org.specnaz.impl.TestCaseType;
import org.specnaz.params.RunnableParams6;

import java.util.List;

public class ParametrizedSubgroup6<P1, P2, P3, P4, P5, P6> extends AbstractParametrizedSubgroup {
    private final RunnableParams6<P1, P2, P3, P4, P5, P6> specClosure;

    public ParametrizedSubgroup6(String description,
            RunnableParams6<P1, P2, P3, P4, P5, P6> specClosure, TestCaseType testCaseType) {
        super(description, testCaseType);
        this.specClosure = specClosure;
    }

    @Override
    protected Runnable toSpecClosure(List<?> paramsSet) {
        return () -> specClosure.invoke((P1) paramsSet.get(0), (P2) paramsSet.get(1),
                (P3) paramsSet.get(2), (P4) paramsSet.get(3), (P5) paramsSet.get(4), (P6) paramsSet.get(5));
    }
}
