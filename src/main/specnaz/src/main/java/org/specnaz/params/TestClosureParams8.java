package org.specnaz.params;

/**
 * The equivalent of {@link org.specnaz.utils.TestClosure} for
 * parametrized tests taking 8 parameters.
 *
 * @see #invoke
 * @see org.specnaz.utils.TestClosure
 * @see ParamsSpecBuilder#should(String, TestClosureParams8)
 * @see ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams8)
 */
@FunctionalInterface
public interface TestClosureParams8<P1, P2, P3, P4, P5, P6, P7, P8> {
    /**
     * The abstract method of the {@link FunctionalInterface}.
     *
     * @param param1 the first parameter
     * @param param2 the second parameter
     * @param param3 the third parameter
     * @param param4 the fourth parameter
     * @param param5 the fifth parameter
     * @param param6 the sixth parameter
     * @param param7 the seventh parameter
     * @param param8 the eight parameter
     * @throws Exception can safely throw any Exception
     */
    void invoke(P1 param1, P2 param2, P3 param3, P4 param4, P5 param5, P6 param6, P7 param7, P8 param8) throws Exception;
}
