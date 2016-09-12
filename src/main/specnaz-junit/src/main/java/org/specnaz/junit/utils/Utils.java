package org.specnaz.junit.utils;

import static java.lang.String.format;

/**
 * A static utility class.
 *
 * @see Utils#instantiateTestClass
 */
public final class Utils {
    /**
     * Instantiates the given {@code testClass} with the no-argument constructor, using reflection.
     *
     * @param testClass
     *     the class of the test object
     * @param targetClass
     *     the desired class of {@code testClass}
     * @param <T>
     *     the type of the instance that we want to instantiate
     * @return
     *     an object of type {@code T}
     * @throws IllegalArgumentException
     *     if either {@code testClass} is not a subtype of {@code targetClass}, or
     *     if the instantiation with the no-argument constructor failed
     *     (the root cause will be available via the {@link Throwable#getCause} method of the exception)
     */
    public static <T> T instantiateTestClass(Class<?> testClass, Class<? extends T> targetClass) throws IllegalArgumentException {
        String className = testClass.getSimpleName();
        if (targetClass.isAssignableFrom(testClass)) {
            Class<? extends T> specClass = testClass.asSubclass(targetClass);
            try {
                return specClass.newInstance();
            } catch (Exception e) {
                throw new IllegalArgumentException(format(
                        "Could not instantiate test class '%s' with no-argument constructor", className),
                        e);
            }
        } else {
            throw new IllegalArgumentException(format(
                    "Expected test class to be a subtype of '%s'; %s is not", targetClass.getSimpleName(), className));
        }
    }

    Utils() {
        throw new UnsupportedOperationException("Cannot instantiate Utils");
    }
}
