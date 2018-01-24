package org.specnaz.junit.impl;

import org.junit.runner.Description;
import org.specnaz.impl.SingleTestCase;

import java.util.IdentityHashMap;

public final class TestCases2DescriptionsMap {
    public static final class Builder {
        private final IdentityHashMap<SingleTestCase, Description> testCases = new IdentityHashMap<>(),
                teardowns = new IdentityHashMap<>();

        public void addDescMapping(SingleTestCase testCase, Description description) {
            testCases.put(testCase, description);
        }

        public void addTeardownMapping(SingleTestCase testCase, Description description) {
            teardowns.put(testCase, description);
        }

        public TestCases2DescriptionsMap build() {
            return new TestCases2DescriptionsMap(testCases, teardowns);
        }
    }

    private final IdentityHashMap<SingleTestCase, Description> testCases, teardowns;

    private TestCases2DescriptionsMap(IdentityHashMap<SingleTestCase, Description> testCases,
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
