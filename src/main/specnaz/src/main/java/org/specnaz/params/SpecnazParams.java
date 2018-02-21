package org.specnaz.params;

import org.specnaz.SpecBuilder;
import org.specnaz.Specnaz;
import org.specnaz.impl.SpecsRegistry;
import org.specnaz.impl.SpecsRegistryViolation;
import org.specnaz.params.impl.ParamsSpecBuilderCoreDslAdapter;

import java.util.function.Consumer;

/**
 * The parametrized equivalent of the {@link Specnaz} interface.
 * You can implement it directly, or through a framework helper like {@code SpecnazParamsJUnit}.
 * <p>
 * It works the same as {@link Specnaz}, except the parameter
 * of the lambda expression passed in {@link #describes} is of type
 * {@link ParamsSpecBuilder} instead of {@link SpecBuilder}.
 * {@link #describes} also obeys the same contract as {@link Specnaz#describes}
 * (must be called in the public, no-argument constructor of the test class exactly once).
 *
 * @see #describes
 * @see #xdescribes
 * @see Specnaz
 */
public interface SpecnazParams {
    /**
     * The parametrized equivalent of {@link Specnaz#describes}.
     *
     * @param description
     *     the top-level description of the spec
     * @param specClosure
     *     a lambda expression taking a {@link ParamsSpecBuilder} as the only argument
     *     and using it to construct the specification
     *
     * @see Specnaz#describes
     */
    default void describes(String description, Consumer<ParamsSpecBuilder> specClosure) {
        try {
            SpecsRegistry.register(this, description, false, coreDslBuilder -> {
                specClosure.accept(new ParamsSpecBuilderCoreDslAdapter(coreDslBuilder));
            });
        } catch (SpecsRegistryViolation e) {
            throw new IllegalStateException("SpecnazParams.describes() was called multiple times in the " +
                    "no-argument constructor of " + this.getClass().getSimpleName());
        }
    }

    /**
     * Allows you to ignore an entire parametrized spec defined in this class.
     *
     * @param description
     *     the top-level description of the spec
     * @param specClosure
     *     a lambda expression taking a {@link ParamsSpecBuilder} as the only argument
     *     and using it to construct the specification
     *
     * @see Specnaz#xdescribes
     */
    default void xdescribes(String description, Consumer<ParamsSpecBuilder> specClosure) {
        try {
            SpecsRegistry.register(this, description, true, coreDslBuilder -> {
                specClosure.accept(new ParamsSpecBuilderCoreDslAdapter(coreDslBuilder));
            });
        } catch (SpecsRegistryViolation e) {
            throw new IllegalStateException("SpecnazParams.describes() was called multiple times in the " +
                    "no-argument constructor of " + this.getClass().getSimpleName());
        }
    }
}
