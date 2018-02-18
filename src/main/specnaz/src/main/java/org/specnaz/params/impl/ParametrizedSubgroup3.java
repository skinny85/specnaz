package org.specnaz.params.impl;

import org.specnaz.impl.TestCaseType;
import org.specnaz.params.RunnableParams3;

import java.util.List;

public final class ParametrizedSubgroup3<P1, P2, P3> extends AbstractParametrizedSubgroup {
    private final RunnableParams3<P1, P2, P3> specClosure;

    public ParametrizedSubgroup3(String description, RunnableParams3<P1, P2, P3> specClosure,
            TestCaseType testCaseType) {
        super(description, testCaseType);
        this.specClosure = specClosure;
    }

    @Override
    protected Runnable toSpecClosure(List<?> paramsSet) {
        return () -> specClosure.invoke((P1) paramsSet.get(0), (P2) paramsSet.get(1),
                (P3) paramsSet.get(2));
    }
}
