package org.specnaz.params;

/**
 * The equivalent of {@link org.specnaz.utils.TestClosure} for
 * parametrized tests taking 3 parameters.
 *
 * @see #invoke
 * @see org.specnaz.utils.TestClosure
 * @see ParamsSpecBuilder#should(String, TestClosureParams3)
 * @see ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams3)
 */
@FunctionalInterface
public interface TestClosureParams3<P1, P2, P3> {
    /**
     * The abstract method of the {@link FunctionalInterface}.
     *
     * @param param1 the first parameter
     * @param param2 the second parameter
     * @param param3 the third parameter
     * @throws Exception can safely throw any Exception
     */
    void invoke(P1 param1, P2 param2, P3 param3) throws Exception;
}
