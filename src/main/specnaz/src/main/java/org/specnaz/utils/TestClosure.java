package org.specnaz.utils;

/**
 * A simple {@link FunctionalInterface}, similar to
 * {@link Runnable}, except it allows you to throw any
 * Exception in the body of the method.
 * Used for lifecycle callbacks ({@code begins/ends})
 * and test bodies.
 */
@FunctionalInterface
public interface TestClosure {
    /**
     * Invokes the callback.
     *
     * @throws Exception you can safely throw any Exception
     *     in the method body - it will be caught and processed
     *     by the library (usually resulting in a test failure)
     */
    void invoke() throws Exception;
}
