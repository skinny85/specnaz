package org.specnaz.params;

/**
 * The equivalent of {@link org.specnaz.utils.TestClosure} for
 * parametrized tests taking 2 parameters.
 *
 * @see #invoke
 * @see org.specnaz.utils.TestClosure
 * @see ParamsSpecBuilder#should(String, TestClosureParams2)
 * @see ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams2)
 */
@FunctionalInterface
public interface TestClosureParams2<P1, P2> {
    /**
     * The abstract method of the {@link FunctionalInterface}.
     *
     * @param param1 the first parameter
     * @param param2 the second parameter
     * @throws Exception can safely throw any Exception
     */
    void invoke(P1 param1, P2 param2) throws Exception;
}
