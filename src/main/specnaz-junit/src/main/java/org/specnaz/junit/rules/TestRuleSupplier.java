package org.specnaz.junit.rules;

import org.junit.rules.TestRule;

/**
 * A {@link FunctionalInterface} used as the parameter to the
 * {@link Rule#of(TestRuleSupplier)} static factory method.
 *
 * @param <T>
 *     the type of {@link TestRule} returned by this Supplier
 */
@FunctionalInterface
public interface TestRuleSupplier<T extends TestRule> {
    T get();
}
