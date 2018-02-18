package org.specnaz.params;

/**
 * The equivalent of {@link Runnable} for parametrized
 * sub-specifications taking one parameter.
 *
 * @see #invoke(P)
 * @see ParamsSpecBuilder#describes(String, RunnableParams1)
 */
@FunctionalInterface
public interface RunnableParams1<P> {
    /**
     * The abstract method of the {@link FunctionalInterface}.
     *
     * @param p the parameter
     */
    void invoke(P p);
}
