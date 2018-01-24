package org.specnaz.junit.rules;

import org.junit.rules.TestRule;

@FunctionalInterface
public interface TestRuleSupplier<T extends TestRule> {
    T get();
}
