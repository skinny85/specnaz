package org.specnaz.params;

/**
 * The equivalent of {@link org.specnaz.utils.TestClosure} for
 * parametrized tests taking 4 parameters.
 *
 * @see #invoke
 * @see org.specnaz.utils.TestClosure
 * @see ParamsSpecBuilder#should(String, TestClosureParams4)
 * @see ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams4)
 */
@FunctionalInterface
public interface TestClosureParams4<P1, P2, P3, P4> {
    /**
     * The abstract method of the {@link FunctionalInterface}.
     *
     * @param param1 the first parameter
     * @param param2 the second parameter
     * @param param3 the third parameter
     * @param param4 the fourth parameter
     * @throws Exception can safely throw any Exception
     */
    void invoke(P1 param1, P2 param2, P3 param3, P4 param4) throws Exception;
}
