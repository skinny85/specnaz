package org.specnaz.params.impl;

import org.specnaz.impl.TestCaseType;

public final class ParametrizedSubgroupInstance {
    public final String description;
    public final Runnable specClosure;
    public final TestCaseType testCaseType;

    public ParametrizedSubgroupInstance(String description, Runnable specClosure, TestCaseType testCaseType) {
        this.description = description;
        this.specClosure = specClosure;
        this.testCaseType = testCaseType;
    }
}
