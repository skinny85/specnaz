package org.specnaz.params;

/**
 * The equivalent of {@link Runnable} for parametrized
 * sub-specifications taking 3 parameters.
 *
 * @see #invoke(P1, P2, P3)
 * @see ParamsSpecBuilder#describes(String, RunnableParams3)
 */
@FunctionalInterface
public interface RunnableParams3<P1, P2, P3> {
    /**
     * The abstract method of the {@link FunctionalInterface}.
     *
     * @param param1 first parameter
     * @param param2 second parameter
     * @param param3 third parameter
     */
    void invoke(P1 param1, P2 param2, P3 param3);
}
