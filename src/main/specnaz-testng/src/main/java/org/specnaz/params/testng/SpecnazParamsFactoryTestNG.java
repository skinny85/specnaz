package org.specnaz.params.testng;

import org.specnaz.impl.SpecsRegistryViolation;
import org.specnaz.params.SpecnazParams;
import org.specnaz.testng.SpecnazFactoryTestNG;
import org.specnaz.testng.SpecnazTests;
import org.specnaz.testng.impl.SpecnazTestNgSpecFactory;
import org.testng.annotations.Factory;

import java.util.function.Consumer;

/**
 * The main interface for writing parametrized Specnaz specs that execute using
 * <a href="https://testng.org">TestNG</a>.
 * <p>
 * You use it identically as {@link SpecnazFactoryTestNG}:
 * your class must implement this interface
 * (it has one, default, method)
 * and be annotated with {@link org.testng.annotations.Test}.
 * The specification itself looks like any other
 * {@link SpecnazParams} spec: the {@link SpecnazParams#describes}
 * method is called in the public, no-argument constructor exactly once,
 * with the body of the specification.
 *
 * @see SpecnazParams
 * @see #specs
 */
public interface SpecnazParamsFactoryTestNG extends SpecnazParams {
    /**
     * The parametrized equivalent of {@link SpecnazFactoryTestNG#specs}.
     * Same notes apply to this method as they do to
     * {@link SpecnazFactoryTestNG#specs}.
     *
     * @return an array of {@link SpecnazTests} instances,
     *     one for each test in the specification implementing this interface
     * @throws IllegalStateException if the {@link SpecnazParams#describes}
     *     method was never called in the public, no-argument
     *     constructor of the implementing class
     * @see SpecnazParams#describes
     * @see SpecnazFactoryTestNG#specs
     */
    @Factory
    default Object[] specs() {
        try {
            return SpecnazTestNgSpecFactory.specs(this);
        } catch (SpecsRegistryViolation e) {
            throw new IllegalStateException("SpecnazParams.describes() was never called in the " +
                    "no-argument constructor of " + this.getClass().getSimpleName());
        }
    }
}
