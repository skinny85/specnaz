package org.specnaz.params;

import org.specnaz.SpecBuilder;

/**
 * The parametrized equivalent of {@link SpecBuilder}.
 * An instance of this class is passed to the main lambda expression
 * given in {@link SpecnazParams#describes}.
 * It contains parametrized versions of the {@link SpecBuilder#should} and
 * {@link SpecBuilder#shouldThrow} (plus its focused and ignored variants) methods.
 *
 * @see SpecBuilder
 */
public interface ParamsSpecBuilder extends SpecBuilder {
    /**
     * The parametrized version of {@link SpecBuilder#should} that takes a single parameter.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected1} class
     *
     * @see SpecBuilder#should
     * @see #fshould(String, TestClosureParams1)
     * @see #xshould(String, TestClosureParams1)
     */
    <P> ParamsExpected1<P> should(String description, TestClosureParams1<P> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#shouldThrow} that takes a single parameter.
     *
     * @param expectedException
     *     the type of Exception expected by the test
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException1} class
     *
     * @see SpecBuilder#shouldThrow
     * @see #fshouldThrow(Class, String, TestClosureParams1)
     * @see #xshouldThrow(Class, String, TestClosureParams1)
     */
    <T extends Throwable, P> ParamsExpectedException1<T, P> shouldThrow(Class<T> expectedException,
            String description, TestClosureParams1<P> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#fshould} that takes a single parameter.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected1} class
     * @deprecated for the same reasons {@link SpecBuilder#fshould} is
     *
     * @see SpecBuilder#fshould
     */
    @Deprecated
    <P> ParamsExpected1<P> fshould(String description, TestClosureParams1<P> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#fshouldThrow} that takes a single parameter.
     *
     * @param expectedException
     *     the type of Exception expected by the test
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException1} class
     * @deprecated for the same reasons {@link SpecBuilder#fshould} is
     *
     * @see SpecBuilder#fshouldThrow
     */
    @Deprecated
    <T extends Throwable, P> ParamsExpectedException1<T, P> fshouldThrow(Class<T> expectedException,
            String description, TestClosureParams1<P> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#xshould} that takes a single parameter.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected1} class
     *
     * @see SpecBuilder#xshould
     */
    <P> ParamsExpected1<P> xshould(String description, TestClosureParams1<P> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#xshouldThrow} that takes a single parameter.
     *
     * @param expectedException
     *     the type of Exception expected by the test
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException1} class
     *
     * @see SpecBuilder#xshouldThrow
     */
    <T extends Throwable, P> ParamsExpectedException1<T, P> xshouldThrow(Class<T> expectedException,
            String description, TestClosureParams1<P> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#should} that takes 2 parameters.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected2} class
     *
     * @see SpecBuilder#should
     * @see #fshould(String, TestClosureParams2)
     * @see #xshould(String, TestClosureParams2)
     */
    <P1, P2> ParamsExpected2<P1, P2> should(String description, TestClosureParams2<P1, P2> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#shouldThrow} that takes 2 parameters.
     *
     * @param expectedException
     *     the type of Exception expected by the test
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException2} class
     *
     * @see SpecBuilder#shouldThrow
     * @see #fshouldThrow(Class, String, TestClosureParams2)
     * @see #xshouldThrow(Class, String, TestClosureParams2)
     */
    <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> shouldThrow(Class<T> expectedException,
            String description, TestClosureParams2<P1, P2> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#fshould} that takes 2 parameters.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected2} class
     * @deprecated for the same reasons {@link SpecBuilder#fshould} is
     *
     * @see SpecBuilder#fshould
     */
    @Deprecated
    <P1, P2> ParamsExpected2<P1, P2> fshould(String description, TestClosureParams2<P1, P2> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#fshouldThrow} that takes 2 parameters.
     *
     * @param expectedException
     *     the type of Exception expected by the test
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException2} class
     * @deprecated for the same reasons {@link SpecBuilder#fshould} is
     *
     * @see SpecBuilder#fshouldThrow
     */
    @Deprecated
    <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> fshouldThrow(Class<T> expectedException,
            String description, TestClosureParams2<P1, P2> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#xshould} that takes 2 parameters.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected2} class
     *
     * @see SpecBuilder#xshould
     */
    <P1, P2> ParamsExpected2<P1, P2> xshould(String description, TestClosureParams2<P1, P2> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#xshouldThrow} that takes two parameters.
     *
     * @param expectedException
     *     the type of Exception expected by the test
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException2} class
     *
     * @see SpecBuilder#xshouldThrow
     */
    <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> xshouldThrow(Class<T> expectedException,
            String description, TestClosureParams2<P1, P2> testBody);
}
