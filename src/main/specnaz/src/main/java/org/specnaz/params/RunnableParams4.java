package org.specnaz.params;

/**
 * The equivalent of {@link Runnable} for parametrized
 * sub-specifications taking 4 parameters.
 *
 * @see #invoke(P1, P2, P3, P4)
 * @see ParamsSpecBuilder#describes(String, RunnableParams4)
 */
@FunctionalInterface
public interface RunnableParams4<P1, P2, P3, P4> {
    /**
     * The abstract method of the {@link FunctionalInterface}.
     *
     * @param param1 first parameter
     * @param param2 second parameter
     * @param param3 third parameter
     * @param param4 fourth parameter
     */
    void invoke(P1 param1, P2 param2, P3 param3, P4 param4);
}
