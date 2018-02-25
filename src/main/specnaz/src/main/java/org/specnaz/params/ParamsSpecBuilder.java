package org.specnaz.params;

import org.specnaz.SpecBuilder;

/**
 * The parametrized equivalent of {@link SpecBuilder}.
 * An instance of this class is passed to the main lambda expression
 * given in {@link SpecnazParams#describes}.
 * It contains parametrized versions of the {@link SpecBuilder#should},
 * {@link SpecBuilder#shouldThrow} and {@link SpecBuilder#describes} methods,
 * plus their focused and ignored variants.
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
     * The parametrized version of {@link SpecBuilder#should} that takes 4 parameters.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected4} class
     *
     * @see SpecBuilder#should
     * @see #fshould(String, TestClosureParams4)
     * @see #xshould(String, TestClosureParams4)
     */
    <P1, P2, P3, P4> ParamsExpected4<P1, P2, P3, P4> should(String description,
            TestClosureParams4<P1, P2, P3, P4> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#fshould} that takes 4 parameters.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected4} class
     *
     * @see SpecBuilder#fshould
     * @see #should(String, TestClosureParams4)
     * @see #xshould(String, TestClosureParams4)
     * @deprecated for the same reasons {@link SpecBuilder#fshould} is
     */
    @Deprecated
    <P1, P2, P3, P4> ParamsExpected4<P1, P2, P3, P4> fshould(String description,
            TestClosureParams4<P1, P2, P3, P4> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#xshould} that takes 4 parameters.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected4} class
     *
     * @see SpecBuilder#xshould
     * @see #should(String, TestClosureParams4)
     * @see #fshould(String, TestClosureParams4)
     */
    <P1, P2, P3, P4> ParamsExpected4<P1, P2, P3, P4> xshould(String description,
            TestClosureParams4<P1, P2, P3, P4> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#shouldThrow} that takes 4 parameters.
     *
     * @param expectedException
     *     the type of Exception expected by the test
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException4} class
     *
     * @see SpecBuilder#shouldThrow
     * @see #fshouldThrow(Class, String, TestClosureParams4)
     * @see #xshouldThrow(Class, String, TestClosureParams4)
     */
    <T extends Throwable, P1, P2, P3, P4> ParamsExpectedException4<T, P1, P2, P3, P4> shouldThrow(
            Class<T> expectedException, String description, TestClosureParams4<P1, P2, P3, P4> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#fshouldThrow} that takes 4 parameters.
     *
     * @param expectedException
     *     the type of Exception expected by the test
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException4} class
     *
     * @see SpecBuilder#fshouldThrow
     * @see #shouldThrow(Class, String, TestClosureParams4)
     * @see #xshouldThrow(Class, String, TestClosureParams4)
     * @deprecated for the same reasons {@link SpecBuilder#fshould} is
     */
    @Deprecated
    <T extends Throwable, P1, P2, P3, P4> ParamsExpectedException4<T, P1, P2, P3, P4> fshouldThrow(
            Class<T> expectedException, String description, TestClosureParams4<P1, P2, P3, P4> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#xshouldThrow} that takes 4 parameters.
     *
     * @param expectedException
     *     the type of Exception expected by the test
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException4} class
     *
     * @see SpecBuilder#xshouldThrow
     * @see #shouldThrow(Class, String, TestClosureParams4)
     * @see #fshouldThrow(Class, String, TestClosureParams4)
     */
    <T extends Throwable, P1, P2, P3, P4> ParamsExpectedException4<T, P1, P2, P3, P4> xshouldThrow(
            Class<T> expectedException, String description, TestClosureParams4<P1, P2, P3, P4> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#should} that takes 5 parameters.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected5} class
     *
     * @see SpecBuilder#should
     * @see #fshould(String, TestClosureParams5)
     * @see #xshould(String, TestClosureParams5)
     */
    <P1, P2, P3, P4, P5> ParamsExpected5<P1, P2, P3, P4, P5> should(String description,
            TestClosureParams5<P1, P2, P3, P4, P5> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#fshould} that takes 5 parameters.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected5} class
     *
     * @see SpecBuilder#fshould
     * @see #should(String, TestClosureParams5)
     * @see #xshould(String, TestClosureParams5)
     * @deprecated for the same reasons {@link SpecBuilder#fshould} is
     */
    @Deprecated
    <P1, P2, P3, P4, P5> ParamsExpected5<P1, P2, P3, P4, P5> fshould(String description,
            TestClosureParams5<P1, P2, P3, P4, P5> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#xshould} that takes 5 parameters.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected5} class
     *
     * @see SpecBuilder#xshould
     * @see #should(String, TestClosureParams5)
     * @see #fshould(String, TestClosureParams5)
     */
    <P1, P2, P3, P4, P5> ParamsExpected5<P1, P2, P3, P4, P5> xshould(String description,
            TestClosureParams5<P1, P2, P3, P4, P5> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#shouldThrow} that takes 5 parameters.
     *
     * @param expectedException
     *     the type of Exception expected by the test
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException5} class
     *
     * @see SpecBuilder#shouldThrow
     * @see #fshouldThrow(Class, String, TestClosureParams5)
     * @see #xshouldThrow(Class, String, TestClosureParams5)
     */
    <T extends Throwable, P1, P2, P3, P4, P5> ParamsExpectedException5<T, P1, P2, P3, P4, P5> shouldThrow(
            Class<T> expectedException, String description, TestClosureParams5<P1, P2, P3, P4, P5> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#fshouldThrow} that takes 5 parameters.
     *
     * @param expectedException
     *     the type of Exception expected by the test
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException5} class
     *
     * @see SpecBuilder#fshouldThrow
     * @see #shouldThrow(Class, String, TestClosureParams5)
     * @see #xshouldThrow(Class, String, TestClosureParams5)
     * @deprecated for the same reasons {@link SpecBuilder#fshould} is
     */
    @Deprecated
    <T extends Throwable, P1, P2, P3, P4, P5> ParamsExpectedException5<T, P1, P2, P3, P4, P5> fshouldThrow(
            Class<T> expectedException, String description, TestClosureParams5<P1, P2, P3, P4, P5> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#xshouldThrow} that takes 5 parameters.
     *
     * @param expectedException
     *     the type of Exception expected by the test
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException5} class
     *
     * @see SpecBuilder#xshouldThrow
     * @see #shouldThrow(Class, String, TestClosureParams5)
     * @see #fshouldThrow(Class, String, TestClosureParams5)
     */
    <T extends Throwable, P1, P2, P3, P4, P5> ParamsExpectedException5<T, P1, P2, P3, P4, P5> xshouldThrow(
            Class<T> expectedException, String description, TestClosureParams5<P1, P2, P3, P4, P5> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#should} that takes 6 parameters.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected6} class
     *
     * @see SpecBuilder#should
     * @see #fshould(String, TestClosureParams6)
     * @see #xshould(String, TestClosureParams6)
     */
    <P1, P2, P3, P4, P5, P6> ParamsExpected6<P1, P2, P3, P4, P5, P6> should(String description,
            TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#fshould} that takes 6 parameters.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected6} class
     *
     * @see SpecBuilder#fshould
     * @see #should(String, TestClosureParams6)
     * @see #xshould(String, TestClosureParams6)
     * @deprecated for the same reasons {@link SpecBuilder#fshould} is
     */
    @Deprecated
    <P1, P2, P3, P4, P5, P6> ParamsExpected6<P1, P2, P3, P4, P5, P6> fshould(String description,
            TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#xshould} that takes 6 parameters.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected6} class
     *
     * @see SpecBuilder#xshould
     * @see #should(String, TestClosureParams6)
     * @see #fshould(String, TestClosureParams6)
     */
    <P1, P2, P3, P4, P5, P6> ParamsExpected6<P1, P2, P3, P4, P5, P6> xshould(String description,
            TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#shouldThrow} that takes 6 parameters.
     *
     * @param expectedException
     *     the type of Exception expected by the test
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException6} class
     *
     * @see SpecBuilder#shouldThrow
     * @see #fshouldThrow(Class, String, TestClosureParams6)
     * @see #xshouldThrow(Class, String, TestClosureParams6)
     */
    <T extends Throwable, P1, P2, P3, P4, P5, P6> ParamsExpectedException6<T, P1, P2, P3, P4, P5, P6> shouldThrow(
            Class<T> expectedException, String description, TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#fshouldThrow} that takes 6 parameters.
     *
     * @param expectedException
     *     the type of Exception expected by the test
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException6} class
     *
     * @see SpecBuilder#fshouldThrow
     * @see #shouldThrow(Class, String, TestClosureParams6)
     * @see #xshouldThrow(Class, String, TestClosureParams6)
     * @deprecated for the same reasons {@link SpecBuilder#fshould} is
     */
    @Deprecated
    <T extends Throwable, P1, P2, P3, P4, P5, P6> ParamsExpectedException6<T, P1, P2, P3, P4, P5, P6> fshouldThrow(
            Class<T> expectedException, String description, TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#xshouldThrow} that takes 6 parameters.
     *
     * @param expectedException
     *     the type of Exception expected by the test
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException6} class
     *
     * @see SpecBuilder#xshouldThrow
     * @see #shouldThrow(Class, String, TestClosureParams6)
     * @see #fshouldThrow(Class, String, TestClosureParams6)
     */
    <T extends Throwable, P1, P2, P3, P4, P5, P6> ParamsExpectedException6<T, P1, P2, P3, P4, P5, P6> xshouldThrow(
            Class<T> expectedException, String description, TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#should} that takes 7 parameters.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected7} class
     *
     * @see SpecBuilder#should
     * @see #fshould(String, TestClosureParams7)
     * @see #xshould(String, TestClosureParams7)
     */
    <P1, P2, P3, P4, P5, P6, P7> ParamsExpected7<P1, P2, P3, P4, P5, P6, P7> should(String description,
            TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody);

    /**
     * The parametrized version of {@link SpecBuilder#fshould} that takes 7 parameters.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected7} class
     *
     * @see SpecBuilder#fshould
     * @see #should(String, TestClosureParams7)
     * @see #xshould(String, TestClosureParams7)
     * @deprecated for the same reasons {@link SpecBuilder#fshould} is
     */
    @Deprecated
    <P1, P2, P3, P4, P5, P6, P7> ParamsExpected7<P1, P2, P3, P4, P5, P6, P7> fshould(String description,
            TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody);
    /**
     * The parametrized version of {@link SpecBuilder#xshould} that takes 7 parameters.
     *
     * @param description
     *     the description of the test
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected7} class
     *
     * @see SpecBuilder#xshould
     * @see #should(String, TestClosureParams7)
     * @see #fshould(String, TestClosureParams7)
     */
    <P1, P2, P3, P4, P5, P6, P7> ParamsExpected7<P1, P2, P3, P4, P5, P6, P7> xshould(String description,
            TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody);

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

    /**
     * The parametrized version of {@link SpecBuilder#describes} that takes 2 parameters.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup2} class
     *
     * @see SpecBuilder#describes
     * @see #fdescribes(String, RunnableParams2)
     * @see #xdescribes(String, RunnableParams2)
     */
    <P1, P2> ParamsExpectedSubgroup2<P1, P2> describes(String description, RunnableParams2<P1, P2> specClosure);

    /**
     * The parametrized version of {@link SpecBuilder#fdescribes} that takes 2 parameters.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup2} class
     *
     * @see SpecBuilder#fdescribes
     * @see #describes(String, RunnableParams2)
     * @see #xdescribes(String, RunnableParams2)
     * @deprecated for the same reasons {@link SpecBuilder#fshould} is
     */
    @Deprecated
    <P1, P2> ParamsExpectedSubgroup2<P1, P2> fdescribes(String description, RunnableParams2<P1, P2> specClosure);

    /**
     * The parametrized version of {@link SpecBuilder#xdescribes} that takes 2 parameters.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup2} class
     *
     * @see SpecBuilder#xdescribes
     * @see #describes(String, RunnableParams2)
     * @see #fdescribes(String, RunnableParams2)
     */
    <P1, P2> ParamsExpectedSubgroup2<P1, P2> xdescribes(String description, RunnableParams2<P1, P2> specClosure);

    /**
     * The parametrized version of {@link SpecBuilder#describes} that takes 3 parameters.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup3} class
     *
     * @see SpecBuilder#describes
     * @see #fdescribes(String, RunnableParams3)
     * @see #xdescribes(String, RunnableParams3)
     */
    <P1, P2, P3> ParamsExpectedSubgroup3<P1, P2, P3> describes(String description, RunnableParams3<P1, P2, P3> specClosure);

    /**
     * The parametrized version of {@link SpecBuilder#fdescribes} that takes 3 parameters.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup3} class
     *
     * @see SpecBuilder#fdescribes
     * @see #describes(String, RunnableParams3)
     * @see #xdescribes(String, RunnableParams3)
     * @deprecated for the same reasons {@link SpecBuilder#fshould} is
     */
    @Deprecated
    <P1, P2, P3> ParamsExpectedSubgroup3<P1, P2, P3> fdescribes(String description, RunnableParams3<P1, P2, P3> specClosure);

    /**
     * The parametrized version of {@link SpecBuilder#xdescribes} that takes 3 parameters.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup3} class
     *
     * @see SpecBuilder#xdescribes
     * @see #describes(String, RunnableParams3)
     * @see #fdescribes(String, RunnableParams3)
     */
    <P1, P2, P3> ParamsExpectedSubgroup3<P1, P2, P3> xdescribes(String description, RunnableParams3<P1, P2, P3> specClosure);

    /**
     * The parametrized version of {@link SpecBuilder#describes} that takes 4 parameters.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup4} class
     *
     * @see SpecBuilder#describes
     * @see #fdescribes(String, RunnableParams4)
     * @see #xdescribes(String, RunnableParams4)
     */
    <P1, P2, P3, P4> ParamsExpectedSubgroup4<P1, P2, P3, P4> describes(String description,
            RunnableParams4<P1, P2, P3, P4> specClosure);

    /**
     * The parametrized version of {@link SpecBuilder#fdescribes} that takes 4 parameters.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup4} class
     *
     * @see SpecBuilder#fdescribes
     * @see #describes(String, RunnableParams4)
     * @see #xdescribes(String, RunnableParams4)
     * @deprecated for the same reasons {@link SpecBuilder#fshould} is
     */
    @Deprecated
    <P1, P2, P3, P4> ParamsExpectedSubgroup4<P1, P2, P3, P4> fdescribes(String description,
            RunnableParams4<P1, P2, P3, P4> specClosure);

    /**
     * The parametrized version of {@link SpecBuilder#xdescribes} that takes 4 parameters.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup4} class
     *
     * @see SpecBuilder#xdescribes
     * @see #describes(String, RunnableParams4)
     * @see #fdescribes(String, RunnableParams4)
     */
    <P1, P2, P3, P4> ParamsExpectedSubgroup4<P1, P2, P3, P4> xdescribes(String description,
            RunnableParams4<P1, P2, P3, P4> specClosure);

    /**
     * The parametrized version of {@link SpecBuilder#describes} that takes 5 parameters.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup5} class
     *
     * @see SpecBuilder#describes
     * @see #fdescribes(String, RunnableParams5)
     * @see #xdescribes(String, RunnableParams5)
     */
    <P1, P2, P3, P4, P5> ParamsExpectedSubgroup5<P1, P2, P3, P4, P5> describes(String description,
            RunnableParams5<P1, P2, P3, P4, P5> specClosure);

    /**
     * The parametrized version of {@link SpecBuilder#fdescribes} that takes 5 parameters.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup5} class
     *
     * @see SpecBuilder#fdescribes
     * @see #describes(String, RunnableParams5)
     * @see #xdescribes(String, RunnableParams5)
     * @deprecated for the same reasons {@link SpecBuilder#fshould} is
     */
    @Deprecated
    <P1, P2, P3, P4, P5> ParamsExpectedSubgroup5<P1, P2, P3, P4, P5> fdescribes(String description,
            RunnableParams5<P1, P2, P3, P4, P5> specClosure);

    /**
     * The parametrized version of {@link SpecBuilder#xdescribes} that takes 5 parameters.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup5} class
     *
     * @see SpecBuilder#xdescribes
     * @see #describes(String, RunnableParams5)
     * @see #fdescribes(String, RunnableParams5)
     */
    <P1, P2, P3, P4, P5> ParamsExpectedSubgroup5<P1, P2, P3, P4, P5> xdescribes(String description,
            RunnableParams5<P1, P2, P3, P4, P5> specClosure);

    /**
     * The parametrized version of {@link SpecBuilder#describes} that takes 6 parameters.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup6} class
     *
     * @see SpecBuilder#describes
     * @see #fdescribes(String, RunnableParams6)
     * @see #xdescribes(String, RunnableParams6)
     */
    <P1, P2, P3, P4, P5, P6> ParamsExpectedSubgroup6<P1, P2, P3, P4, P5, P6> describes(String description,
            RunnableParams6<P1, P2, P3, P4, P5, P6> specClosure);

    /**
     * The parametrized version of {@link SpecBuilder#fdescribes} that takes 6 parameters.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup6} class
     *
     * @see SpecBuilder#fdescribes
     * @see #describes(String, RunnableParams6)
     * @see #xdescribes(String, RunnableParams6)
     * @deprecated for the same reasons {@link SpecBuilder#fshould} is
     */
    @Deprecated
    <P1, P2, P3, P4, P5, P6> ParamsExpectedSubgroup6<P1, P2, P3, P4, P5, P6> fdescribes(String description,
            RunnableParams6<P1, P2, P3, P4, P5, P6> specClosure);

    /**
     * The parametrized version of {@link SpecBuilder#xdescribes} that takes 6 parameters.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup6} class
     *
     * @see SpecBuilder#xdescribes
     * @see #describes(String, RunnableParams6)
     * @see #fdescribes(String, RunnableParams6)
     */
    <P1, P2, P3, P4, P5, P6> ParamsExpectedSubgroup6<P1, P2, P3, P4, P5, P6> xdescribes(String description,
            RunnableParams6<P1, P2, P3, P4, P5, P6> specClosure);
}
