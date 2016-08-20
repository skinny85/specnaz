package org.specnaz.impl;

import org.specnaz.SpecBuilder;
import org.specnaz.utils.TestClosure;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public final class ListSpecBuilder implements SpecBuilder {
    private final List<SingleTestCase> testCases = new LinkedList<>();

    @Override
    public void should(String description, TestClosure testBody) {
        testCases.add(new SingleTestCase(description, testBody));
    }

    public List<SingleTestCase> testCases() {
        return Collections.unmodifiableList(testCases);
    }

    @Override
    public void beginsAll(TestClosure closure) {
    }

    @Override
    public void beginsEach(TestClosure closure) {
    }

    @Override
    public void endsEach(TestClosure closure) {
    }

    @Override
    public void endsAll(TestClosure closure) {
    }

    @Override
    public void describes(String description, Runnable specClosure) {
    }
}
