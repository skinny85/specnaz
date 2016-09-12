package org.specnaz;

import org.specnaz.impl.SpecBuilderCoreDslAdapter;
import org.specnaz.impl.SpecsRegistry;
import org.specnaz.impl.SpecsRegistryViolation;

import java.util.function.Consumer;

/**
 * The main interface of the Specnaz library.
 * Every test class must implement this interface -
 * either directly, or indirectly (for example, through
 * a framework helper like {@code SpecnazJUnit}) -
 * to be eligible for running by the library
 * (otherwise, you will get an exception during test initialization).
 * <p>
 * It contains one method with a default implementation,
 * so does not require any code in the implementing class.
 * <p>
 * The contract of the interface is that the test class must
 * have a public, no-argument constructor.
 * In that constructor, the {@link Specnaz#describes} method must be called exactly once.
 *
 * @see Specnaz#describes
 */
public interface Specnaz {
    /**
     * The method used to create the specification.
     * Must be called from the test classes' no-argument constructor exactly once
     * (calling it zero or more than one time will result in an error).
     * <p>
     * It's a default method, and should never be overridden.
     * Also, do not rely on the implementation -
     * it is not part of the public contract of the method,
     * and can and will change between Specnaz versions.
     *
     * @param description
     *     the top-level description of the spec
     * @param specClosure
     *     an instance of {@link Consumer}, most likely passed as a
     *     lambda expression, that takes a {@link SpecBuilder} as the only
     *     argument, and uses it to create the specification.
     *     <p>
     *     Note that while the lifecycle callbacks and the test bodies can
     *     throw any exception, {@code specClosure} should never throw
     *     (that's why it's an interface not declaring any exception) -
     *     doing so will result in an error during the test initialization phase.
     */
    default void describes(String description, Consumer<SpecBuilder> specClosure) {
        try {
            SpecsRegistry.register(this, description, coreDslBuilder -> {
                specClosure.accept(new SpecBuilderCoreDslAdapter(coreDslBuilder));
            });
        } catch (SpecsRegistryViolation e) {
            throw new IllegalStateException("Specnaz.describes() was called multiple times in the " +
                    "no-argument constructor of " + this.getClass().getSimpleName());
        }
    }
}
