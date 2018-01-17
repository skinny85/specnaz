package org.specnaz.junit.impl;

import org.junit.runner.Description;
import org.specnaz.impl.SingleTestCase;

import java.util.IdentityHashMap;

public final class TestCase2DescriptionMap {
    public static final class Builder {
        private final IdentityHashMap<SingleTestCase, Description> testCases = new IdentityHashMap<>(),
                teardowns = new IdentityHashMap<>();

        public void addDescMapping(SingleTestCase testCase, Description description) {
            testCases.put(testCase, description);
        }

        public void addTeardownMapping(SingleTestCase testCase, Description description) {
            teardowns.put(testCase, description);
        }

        public TestCase2DescriptionMap build() {
            return new TestCase2DescriptionMap(testCases, teardowns);
        }
    }

    private final IdentityHashMap<SingleTestCase, Description> testCases, teardowns;

    public TestCase2DescriptionMap(IdentityHashMap<SingleTestCase, Description> testCases,
            IdentityHashMap<SingleTestCase, Description> teardowns) {
        this.testCases = testCases;
        this.teardowns = teardowns;
    }

    public Description findDesc(SingleTestCase testCase) {
        return testCases.get(testCase);
    }

    public Description findTeardown(SingleTestCase testCase) {
        return teardowns.get(testCase);
    }
}
