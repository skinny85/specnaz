package org.specnaz.junit.rules;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.util.function.Supplier;

public final class MethodRuleHolder<T extends MethodRule> {
    private final Supplier<T> factory;
    private T current;

    public MethodRuleHolder(Supplier<T> factory) {
        this.factory = factory;
    }

    public T r() {
        return current;
    }

    private void reset() {
        current = factory.get();
    }

    public final class Wrapper {
        public void reset() {
            MethodRuleHolder.this.reset();
        }

        public Statement apply(Statement statement, FrameworkMethod frameworkMethod, Object instance) {
            return MethodRuleHolder.this.current.apply(statement, frameworkMethod, instance);
        }
    }
}
