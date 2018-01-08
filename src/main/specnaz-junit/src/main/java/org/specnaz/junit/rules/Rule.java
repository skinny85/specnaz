package org.specnaz.junit.rules;

import org.junit.rules.MethodRule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public abstract class Rule<T> {
    public static <T extends MethodRule> Rule<T> of(MethodRuleSupplier<T> factory) {
        return new InstanceMethodRule<>(factory);
    }

    public static <T extends TestRule> Rule<T> of(TestRuleSupplier<T> factory) {
        return new InstanceTestRule<>(factory);
    }

    protected T current;

    Rule() {
    }

    public T r() {
        return current;
    }

    private void reset() {
        current = create();
    }

    abstract T create();

    abstract Statement apply(Statement statement, Description description, FrameworkMethod frameworkMethod, Object target);

    public final class Wrapper {
        public void reset() {
            Rule.this.reset();
        }

        public Statement apply(Statement statement, Description description, FrameworkMethod frameworkMethod, Object target) {
            return Rule.this.apply(statement, description, frameworkMethod, target);
        }
    }
}

final class InstanceMethodRule<T extends MethodRule> extends Rule<T> {
    private final MethodRuleSupplier<T> factory;

    InstanceMethodRule(MethodRuleSupplier<T> factory) {
        this.factory = factory;
    }

    @Override
    T create() {
        return factory.get();
    }

    @Override
    Statement apply(Statement statement, Description description, FrameworkMethod frameworkMethod, Object target) {
        return current.apply(statement, frameworkMethod, target);
    }
}

final class InstanceTestRule<T extends TestRule> extends Rule<T> {
    private final TestRuleSupplier<T> factory;

    InstanceTestRule(TestRuleSupplier<T> factory) {
        this.factory = factory;
    }

    @Override
    T create() {
        return factory.get();
    }

    @Override
    Statement apply(Statement statement, Description description, FrameworkMethod frameworkMethod, Object target) {
        return current.apply(statement, description);
    }
}
