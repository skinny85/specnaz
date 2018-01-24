package org.specnaz.junit.rules;

import org.junit.rules.MethodRule;

@FunctionalInterface
public interface MethodRuleSupplier<T extends MethodRule> {
    T get();
}
