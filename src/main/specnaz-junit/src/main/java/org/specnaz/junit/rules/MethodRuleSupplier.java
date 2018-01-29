package org.specnaz.junit.rules;

import org.junit.rules.MethodRule;

/**
 * A {@link FunctionalInterface} used as the parameter to the
 * {@link Rule#of(MethodRuleSupplier)} static factory method.
 *
 * @param <T>
 *     the type of {@link MethodRule} returned by this Supplier
 */
@FunctionalInterface
public interface MethodRuleSupplier<T extends MethodRule> {
    T get();
}
