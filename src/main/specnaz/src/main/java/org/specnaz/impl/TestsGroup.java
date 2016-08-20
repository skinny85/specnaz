package org.specnaz.impl;

import org.specnaz.utils.TestClosure;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public final class TestsGroup {
    public final String description;
    public final List<TestClosure> beforeAlls,
                                   befores,
                                   afters,
                                   afterAlls;
    public final List<SingleTestCase> testCases;
    public final int testsInTree;

    public TestsGroup(String description,
                      List<TestClosure> beforeAlls,
                      List<TestClosure> befores,
                      List<SingleTestCase> testCases,
                      List<TestClosure> afters,
                      List<TestClosure> afterAlls,
                      int testsInSubgroups) {
        this.description = description;
        this.beforeAlls = copyToImmutableList(beforeAlls);
        this.befores = copyToImmutableList(befores);
        this.testCases = copyToImmutableList(testCases);
        this.afters = copyToImmutableList(afters);
        this.afterAlls = copyToImmutableList(afterAlls);
        this.testsInTree = testsInSubgroups + testCases.size();
    }

    private <T> List<T> copyToImmutableList(List<T> list) {
        return Collections.unmodifiableList(new LinkedList<>(list));
    }
}
