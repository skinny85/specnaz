package org.specnaz.params.impl;

import org.specnaz.impl.TestCaseType;
import org.specnaz.params.RunnableParams4;

import java.util.List;

public final class ParametrizedSubgroup4<P1, P2, P3, P4> extends AbstractParametrizedSubgroup {
    private final RunnableParams4<P1, P2, P3, P4> specClosure;

    public ParametrizedSubgroup4(String description, RunnableParams4<P1, P2, P3, P4> specClosure,
            TestCaseType testCaseType) {
        super(description, testCaseType);
        this.specClosure = specClosure;
    }

    @Override
    protected Runnable toSpecClosure(List<?> paramsSet) {
        return () -> specClosure.invoke((P1) paramsSet.get(0), (P2) paramsSet.get(1),
                (P3) paramsSet.get(2), (P4) paramsSet.get(3));
    }
}
