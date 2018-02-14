package org.specnaz.params;

/**
 * The equivalent of {@link org.specnaz.utils.TestClosure} for
 * parametrized tests taking one parameter.
 *
 * @see #invoke
 * @see org.specnaz.utils.TestClosure
 * @see ParamsSpecBuilder#should(String, TestClosureParams1)
 * @see ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams1)
 */
@FunctionalInterface
public interface TestClosureParams1<P> {
    /**
     * The abstract method of the {@link FunctionalInterface}.
     *
     * @param p the parameter
     * @throws Exception can safely throw any Exception
     */
    void invoke(P p) throws Exception;
}
