package org.specnaz.params;

/**
 * The equivalent of {@link Runnable} for parametrized
 * sub-specifications taking 2 parameters.
 *
 * @see #invoke(P1, P2)
 * @see ParamsSpecBuilder#describes(String, RunnableParams2)
 */
@FunctionalInterface
public interface RunnableParams2<P1, P2> {
    /**
     * The abstract method of the {@link FunctionalInterface}.
     *
     * @param param1 first parameter
     * @param param2 second parameter
     */
    void invoke(P1 param1, P2 param2);
}
