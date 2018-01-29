package org.specnaz.utils;

import java.lang.reflect.Method;

/**
 * Some static utility methods helpful when writing tests in Specnaz.
 *
 * @see #findMethod
 */
public final class Utils {
    /**
     * A shorthand useful when setting the test method with {@link org.specnaz.TestSettings#usingMethod}.
     * Allows you to easily refer to a method in the specification class by name.
     * You call it something like this:
     *
     * <pre class="code"><code class="java">
     * public class SomeSpec {
     *       {
     *           describes("some example tests", it -> {
     *               it.should("correctly find the method", () -> {
     *                   // test body...
     *               }).usingMethod(Utils.findMethod(this, "someMethod"));
     *           });
     *       }
     *
     *       &#64;DirtiesContext
     *       public void someMethod() {
     *       }
     * }
     * </code></pre>
     *
     * @param spec
     *     the spec instance
     * @param methodName
     *     the name of the method to find
     * @param parameterTypes
     *     the types of parameters of the method to find
     * @return the desired {@link Method}
     * @throws IllegalArgumentException
     *     if the desired method could not be found
     * @see org.specnaz.TestSettings#usingMethod
     */
    public static Method findMethod(Object spec, String methodName, Class<?>... parameterTypes)
            throws IllegalArgumentException {
        try {
            return spec.getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
    }

    Utils() {
        throw new UnsupportedOperationException("Cannot instantiate Utils");
    }
}
