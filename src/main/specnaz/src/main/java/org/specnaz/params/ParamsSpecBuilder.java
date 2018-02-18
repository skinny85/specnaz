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
     * @see #should(String, TestClosureParams1)
     * @see #xshould(String, TestClosureParams1)
     */
    @Deprecated
    <P> ParamsExpected1<P> fshould(String description, TestClosureParams1<P> testBody);

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
     * @see #should(String, TestClosureParams1)
     * @see #fshould(String, TestClosureParams1)
     */
    <P> ParamsExpected1<P> xshould(String description, TestClosureParams1<P> testBody);

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
     * @see #shouldThrow(Class, String, TestClosureParams1)
     * @see #xshouldThrow(Class, String, TestClosureParams1)
     */
    @Deprecated
    <T extends Throwable, P> ParamsExpectedException1<T, P> fshouldThrow(Class<T> expectedException,
            String description, TestClosureParams1<P> testBody);

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
     * @see #shouldThrow(Class, String, TestClosureParams1)
     * @see #fshouldThrow(Class, String, TestClosureParams1)
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
     * @see #should(String, TestClosureParams2)
     * @see #xshould(String, TestClosureParams2)
     */
    @Deprecated
    <P1, P2> ParamsExpected2<P1, P2> fshould(String description, TestClosureParams2<P1, P2> testBody);

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
     * @see #should(String, TestClosureParams2)
     * @see #fshould(String, TestClosureParams2)
     */
    <P1, P2> ParamsExpected2<P1, P2> xshould(String description, TestClosureParams2<P1, P2> testBody);

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
     * @see #shouldThrow(Class, String, TestClosureParams2)
     * @see #xshouldThrow(Class, String, TestClosureParams2)
     */
    @Deprecated
    <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> fshouldThrow(Class<T> expectedException,
            String description, TestClosureParams2<P1, P2> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#xshouldThrow} that takes 2 parameters.
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
     * @see #shouldThrow(Class, String, TestClosureParams2)
     * @see #fshouldThrow(Class, String, TestClosureParams2)
     */
    <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> xshouldThrow(Class<T> expectedException,
            String description, TestClosureParams2<P1, P2> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#should} that takes 3 parameters.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected3} class
     *
     * @see SpecBuilder#should
     * @see #fshould(String, TestClosureParams3)
     * @see #xshould(String, TestClosureParams3)
     */
    <P1, P2, P3> ParamsExpected3<P1, P2, P3> should(String description, TestClosureParams3<P1, P2, P3> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#fshould} that takes 3 parameters.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected3} class
     * @deprecated for the same reasons {@link SpecBuilder#fshould} is
     *
     * @see SpecBuilder#fshould
     * @see #should(String, TestClosureParams3)
     * @see #xshould(String, TestClosureParams3)
     */
    @Deprecated
    <P1, P2, P3> ParamsExpected3<P1, P2, P3> fshould(String description, TestClosureParams3<P1, P2, P3> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#xshould} that takes 3 parameters.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected3} class
     *
     * @see SpecBuilder#xshould
     * @see #should(String, TestClosureParams3)
     * @see #fshould(String, TestClosureParams3)
     */
    <P1, P2, P3> ParamsExpected3<P1, P2, P3> xshould(String description, TestClosureParams3<P1, P2, P3> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#shouldThrow} that takes 3 parameters.
     *
     * @param expectedException
     *     the type of Exception expected by the test
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException3} class
     *
     * @see SpecBuilder#shouldThrow
     * @see #fshouldThrow(Class, String, TestClosureParams3)
     * @see #xshouldThrow(Class, String, TestClosureParams3)
     */
    <T extends Throwable, P1, P2, P3> ParamsExpectedException3<T, P1, P2, P3> shouldThrow(Class<T> expectedException,
            String description, TestClosureParams3<P1, P2, P3> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#fshouldThrow} that takes 3 parameters.
     *
     * @param expectedException
     *     the type of Exception expected by the test
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException3} class
     * @deprecated for the same reasons {@link SpecBuilder#fshould} is
     *
     * @see SpecBuilder#fshouldThrow
     * @see #shouldThrow(Class, String, TestClosureParams3)
     * @see #xshouldThrow(Class, String, TestClosureParams3)
     */
    @Deprecated
    <T extends Throwable, P1, P2, P3> ParamsExpectedException3<T, P1, P2, P3> fshouldThrow(Class<T> expectedException,
            String description, TestClosureParams3<P1, P2, P3> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#xshouldThrow} that takes 3 parameters.
     *
     * @param expectedException
     *     the type of Exception expected by the test
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException3} class
     *
     * @see SpecBuilder#xshouldThrow
     * @see #shouldThrow(Class, String, TestClosureParams3)
     * @see #fshouldThrow(Class, String, TestClosureParams3)
     */
    <T extends Throwable, P1, P2, P3> ParamsExpectedException3<T, P1, P2, P3> xshouldThrow(Class<T> expectedException,
            String description, TestClosureParams3<P1, P2, P3> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#describes} that takes a single parameter.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup1} class
     *
     * @see SpecBuilder#describes
     * @see #fdescribes(String, RunnableParams1)
     * @see #xdescribes(String, RunnableParams1)
     */
    <P> ParamsExpectedSubgroup1<P> describes(String description, RunnableParams1<P> specClosure);

    /**
     * The parametrized version of {@link SpecBuilder#fdescribes} that takes a single parameter.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup1} class
     * @deprecated for the same reasons {@link SpecBuilder#fshould} is
     *
     * @see SpecBuilder#fdescribes
     * @see #describes(String, RunnableParams1)
     * @see #xdescribes(String, RunnableParams1)
     */
    @Deprecated
    <P> ParamsExpectedSubgroup1<P> fdescribes(String description, RunnableParams1<P> specClosure);

    /**
     * The parametrized version of {@link SpecBuilder#xdescribes} that takes a single parameter.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup1} class
     *
     * @see SpecBuilder#xdescribes
     * @see #describes(String, RunnableParams1)
     * @see #fdescribes(String, RunnableParams1)
     */
    <P> ParamsExpectedSubgroup1<P> xdescribes(String description, RunnableParams1<P> specClosure);
}
