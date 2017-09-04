package org.specnaz.core;

import org.specnaz.Specnaz;
import org.specnaz.impl.SpecsRegistry;
import org.specnaz.impl.SpecsRegistryViolation;
import org.specnaz.impl.TestCaseType;

import java.util.function.Consumer;

/**
 * The core DSL of Specnaz.
 * You should only use it when writing your custom DSL, like this:
 *
 * <pre>
 * {@code
 *     public interface MyCustomDsl extends SpecnazCoreDsl {
 *         default void myEntryMethod(String description, Consumer<MyDslBuilder> specClosure) {
 *             specification(description, specBuilder -> {
 *                 specClosure.accept(new MyDslBuilderAdapter(specBuilder);
 *             }
 *         }
 *      }
 * }
 * </pre>
 *
 * If you're not writing a custom DSL, use the {@link Specnaz} interface instead.
 *
 * @see SpecnazCoreDsl#specification
 */
public interface SpecnazCoreDsl {
    /**
     * The analog of {@link Specnaz#describes} - defines the specification.
     *
     * @param description
     *     the top-level description of the spec
     * @param specClosure
     *     the callback that creates the specification
     */
    default void specification(String description, Consumer<CoreDslBuilder> specClosure) {
        try {
            SpecsRegistry.register(this, description, TestCaseType.REGULAR, specClosure);
        } catch (SpecsRegistryViolation e) {
            throw new IllegalStateException("SpecnazCoreDsl.specification() was called multiple times in the " +
                    "no-argument constructor of " + this.getClass().getSimpleName());
        }
    }
}
