package org.specnaz.params;

/**
 * The equivalent of {@link Runnable} for parametrized
 * sub-specifications taking 9 parameters.
 *
 * @see #invoke
 * @see ParamsSpecBuilder#describes(String, RunnableParams9)
 */
@FunctionalInterface
public interface RunnableParams9<P1, P2, P3, P4, P5, P6, P7, P8, P9> {
    /**
     * The abstract method of the {@link FunctionalInterface}.
     *
     * @param param1 first parameter
     * @param param2 second parameter
     * @param param3 third parameter
     * @param param4 fourth parameter
     * @param param5 fifth parameter
     * @param param6 sixth parameter
     * @param param7 seventh parameter
     * @param param8 eight parameter
     * @param param9 ninth parameter
     */
    void invoke(P1 param1, P2 param2, P3 param3, P4 param4, P5 param5, P6 param6, P7 param7, P8 param8, P9 param9);
}
