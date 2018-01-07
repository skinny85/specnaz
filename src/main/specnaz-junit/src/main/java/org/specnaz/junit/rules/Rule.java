package org.specnaz.junit.rules;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.util.function.Supplier;

public final class Rule<T extends MethodRule> {
    public static <T extends MethodRule> Rule<T> of(Supplier<T> factory) {
        return new Rule<T>(factory);
    }

    private final Supplier<T> factory;
    private T current;

    private Rule(Supplier<T> factory) {
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
            Rule.this.reset();
        }

        public Statement apply(Statement statement, FrameworkMethod frameworkMethod, Object instance) {
            return Rule.this.current.apply(statement, frameworkMethod, instance);
        }
    }
}
