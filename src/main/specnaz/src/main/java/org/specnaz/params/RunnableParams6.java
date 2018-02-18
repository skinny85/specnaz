package org.specnaz.params;

/**
 * The equivalent of {@link Runnable} for parametrized
 * sub-specifications taking 6 parameters.
 *
 * @see #invoke(P1, P2, P3, P4, P5, P6)
 * @see ParamsSpecBuilder#describes(String, RunnableParams6)
 */
@FunctionalInterface
public interface RunnableParams6<P1, P2, P3, P4, P5, P6> {
    /**
     * The abstract method of the {@link FunctionalInterface}.
     *
     * @param param1 first parameter
     * @param param2 second parameter
     * @param param3 third parameter
     * @param param4 fourth parameter
     * @param param5 fifth parameter
     * @param param6 sixth parameter
     */
    void invoke(P1 param1, P2 param2, P3 param3, P4 param4, P5 param5, P6 param6);
}
