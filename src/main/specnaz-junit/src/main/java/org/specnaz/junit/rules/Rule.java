package org.specnaz.junit.rules;

import org.junit.rules.MethodRule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * The API for using JUnit instance (as opposed to class) Rules in Specnaz.
 * You use it by declaring a {@code public} field of this type in your spec class.
 * It will be picked up by the JUnit Runner,
 * and wrap each of your tests' execution in the provided Rule.
 * <p>
 * You construct instances of this class by calling the static factory methods,
 * either {@link #of(TestRuleSupplier)} or {@link #of(MethodRuleSupplier)},
 * depending on whether you're using a {@link TestRule} or {@link MethodRule}, respectively.
 * <p>
 * The Supplier you provide as the argument to {@code of} will be called
 * by the JUnit Runner before every test,
 * in this way ensuring each test gets its own, fresh instance of the Rule.
 * You can access the current instance of the Rule with the {@link #r()} method.
 * <p>
 * Example:
 *
 * <pre class="code"><code class="java">
 * import org.junit.rules.ExpectedException;
 * import org.specnaz.junit.SpecnazJUnit;
 * import org.specnaz.junit.rules.Rule;
 *
 * public class ExpectedExceptionRuleSpec extends SpecnazJUnit {
 *       public Rule&lt;ExpectedException&gt; expectedException = Rule.of(() -> ExpectedException.none());
 *
 *       {
 *           describes("Using the ExpectedException JUnit Rule in Specnaz", it -> {
 *               it.should("correctly set the expected Exception", () -> {
 *                   expectedException.r().expect(IllegalArgumentException.class);
 *                   throw new IllegalArgumentException();
 *               });
 *           });
 *       }
 * }
 * </code></pre>
 *
 * <b>Note</b>: 'vanilla' JUnit allows you to specify instance Rules by annotating methods
 * with {@link org.junit.Rule} as well as fields.
 * That is not supported in Specnaz - you can only define Rules with fields.
 * <p>
 * <b>Note</b>: if you have multiple {@link Rule} fields in the same class,
 * the order in which they will be applied is unspecified -
 * most importantly, it will most likely <b>not</b> be the order in which they were declared in the class.
 * If you need to control that order, look into the {@link org.junit.rules.RuleChain} class.
 *
 * @param <T>
 *     the type of the JUnit Rule
 *     (a subclass of either {@link MethodRule} or {@link TestRule})
 *     contained in this class
 *
 * @see #of(MethodRuleSupplier)
 * @see #of(TestRuleSupplier)
 * @see #r()
 * @see org.junit.Rule
 * @see org.junit.rules.MethodRule
 * @see org.junit.rules.TestRule
 */
public abstract class Rule<T> {
    /**
     * Constructs a new {@link Rule} wrapping the {@link MethodRule} returned by {@code factory}.
     *
     * @param factory
     *     the Supplier lambda expression, providing an instance of {@link MethodRule}
     * @param <T>
     *     the type of the {@link MethodRule} returned by {@code factory}
     * @return a new instance of {@link Rule}
     */
    public static <T extends MethodRule> Rule<T> of(MethodRuleSupplier<T> factory) {
        return new InstanceMethodRule<>(factory);
    }

    /**
     * Constructs a new {@link Rule} wrapping the {@link TestRule} returned by {@code factory}.
     *
     * @param factory
     *     the Supplier lambda expression, providing an instance of {@link TestRule}
     * @param <T>
     *     the type of the {@link TestRule} returned by {@code factory}
     * @return a new instance of {@link Rule}
     */
    public static <T extends TestRule> Rule<T> of(TestRuleSupplier<T> factory) {
        return new InstanceTestRule<>(factory);
    }

    protected T current;

    Rule() {
    }

    /**
     * Allows access to the current instance of the underlying {@link TestRule} or {@link MethodRule}.
     *
     * @return the current instance of the wrapped Rule
     */
    public T r() {
        return current;
    }

    private void reset() {
        current = create();
    }

    abstract T create();

    abstract Statement apply(Statement statement, Description description, FrameworkMethod frameworkMethod, Object target);

    /**
     * The wrapper class used in the Specnaz JUnit Runner.
     * You never need this class when writing tests,
     * it's just an implementation detail.
     */
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
