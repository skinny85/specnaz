package org.specnaz.testng;

import org.specnaz.Specnaz;
import org.specnaz.impl.SpecsRegistryViolation;
import org.specnaz.testng.impl.SpecnazTestNgSpecFactory;
import org.testng.annotations.Factory;

import java.util.function.Consumer;

/**
 * The main interface for using Specnaz with <a href="https://testng.org">TestNG</a>.
 * <p>
 * Your test class needs to implement it
 * (it has one method, {@link #specs}, with a default implementation,
 * so you don't need to write any code in your class to implement it),
 * and also needs to be annotated with TestNG's {@link org.testng.annotations.Test} annotation.
 * Other than that, the test looks exactly like any other Specnaz test -
 * you need to call the {@link Specnaz#describes} method in the public,
 * no-argument constructor of the class exactly once,
 * with the body of your specification
 * (this interface extends {@link Specnaz},
 * so you don't need to implement it separately).
 * <p>
 * Example:
 * <pre class="code"><code class="java">
 * import org.testng.annotations.Test;
 * import org.specnaz.testng.SpecnazFactoryTestNG;
 *
 * &#64;Test
 * public class MySpec implements SpecnazFactoryTestNG {{
 *     describes("My specification", it -&gt; {
 *         // use 'it' here to construct your specification...
 *     });
 * }}
 * </code></pre>
 *
 * @see #specs
 * @see Specnaz
 */
public interface SpecnazFactoryTestNG extends Specnaz {
    /**
     * A TestNG {@link Factory} method that returns the
     * actual test instances that will be executed.
     * Each instance corresponds to a single test
     * (<code>should</code> or <code>shouldThrow</code> method) in the specification.
     * <p>
     * <b>Note</b>: the implementation of this method is not part of its public contract,
     * and is subject to change between Specnaz versions.
     * It should never be overridden.
     *
     * @return an array of {@link SpecnazTests} instances,
     *     one for each test in the specification implementing this interface
     * @throws IllegalStateException if the {@link Specnaz#describes}
     *     method was never called in the public, no-argument
     *     constructor of the implementing class
     * @see Specnaz#describes
     */
    @Factory
    default Object[] specs() throws IllegalStateException {
        try {
            return SpecnazTestNgSpecFactory.specs(this);
        } catch (SpecsRegistryViolation e) {
            throw new IllegalStateException("Specnaz.describes() was never called in the " +
                    "no-argument constructor of " + this.getClass().getSimpleName());
        }
    }
}
