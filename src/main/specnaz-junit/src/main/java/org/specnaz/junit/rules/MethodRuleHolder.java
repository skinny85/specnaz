package org.specnaz.junit.rules;

import org.junit.rules.MethodRule;

import java.util.function.Supplier;

public class MethodRuleHolder<T extends MethodRule> {
    public MethodRuleHolder(Supplier<T> factory) {
    }

    public T r() {
        throw new UnsupportedOperationException();
    }
}
