package org.specnaz.core;

import org.specnaz.SpecBuilder;
import org.specnaz.TestSettings;
import org.specnaz.params.ParamsExpected1;
import org.specnaz.params.ParamsExpected2;
import org.specnaz.params.ParamsExpected3;
import org.specnaz.params.ParamsExpected4;
import org.specnaz.params.ParamsExpected5;
import org.specnaz.params.ParamsExpected6;
import org.specnaz.params.ParamsExpected7;
import org.specnaz.params.ParamsExpected8;
import org.specnaz.params.ParamsExpected9;
import org.specnaz.params.ParamsExpectedException1;
import org.specnaz.params.ParamsExpectedException2;
import org.specnaz.params.ParamsExpectedException3;
import org.specnaz.params.ParamsExpectedException4;
import org.specnaz.params.ParamsExpectedException5;
import org.specnaz.params.ParamsExpectedException6;
import org.specnaz.params.ParamsExpectedException7;
import org.specnaz.params.ParamsExpectedException8;
import org.specnaz.params.ParamsExpectedException9;
import org.specnaz.params.ParamsExpectedSubgroup1;
import org.specnaz.params.ParamsExpectedSubgroup2;
import org.specnaz.params.ParamsExpectedSubgroup3;
import org.specnaz.params.ParamsExpectedSubgroup4;
import org.specnaz.params.ParamsExpectedSubgroup5;
import org.specnaz.params.ParamsExpectedSubgroup6;
import org.specnaz.params.ParamsExpectedSubgroup7;
import org.specnaz.params.ParamsSpecBuilder;
import org.specnaz.params.RunnableParams1;
import org.specnaz.params.RunnableParams2;
import org.specnaz.params.RunnableParams3;
import org.specnaz.params.RunnableParams4;
import org.specnaz.params.RunnableParams5;
import org.specnaz.params.RunnableParams6;
import org.specnaz.params.RunnableParams7;
import org.specnaz.params.TestClosureParams1;
import org.specnaz.params.TestClosureParams2;
import org.specnaz.params.TestClosureParams3;
import org.specnaz.params.TestClosureParams4;
import org.specnaz.params.TestClosureParams5;
import org.specnaz.params.TestClosureParams6;
import org.specnaz.params.TestClosureParams7;
import org.specnaz.params.TestClosureParams8;
import org.specnaz.params.TestClosureParams9;
import org.specnaz.utils.TestClosure;
import org.specnaz.utils.ThrowableExpectations;

/**
 * The core specification Builder.
 * Basically the same as {@link SpecBuilder},
 * just changes the names slightly, using the Jasmine nomenclature -
 * so, {@code beginsAll/Each} is {@code beforeAll/Each} and
 * {@code endsAll/Each} - {@code afterAll/Each}.
 * {@link SpecBuilder#should} becomes {@link CoreDslBuilder#test}
 * (and doesn't prepend 'should' to the description),
 * and {@link SpecBuilder#describes} is {@link CoreDslBuilder#subSpecification}.
 * <p>
 * Same as {@link SpecnazCoreDsl}, you only use this class when implementing your
 * own custom Specnaz DSL.
 *
 * @see SpecnazCoreDsl
 * @see #beforeAll
 * @see #beforeEach
 * @see #test
 * @see #testExpectingException
 * @see #focusedTest
 * @see #focusedTestExpectingException
 * @see #ignoredTest
 * @see #ignoredTestExpectingException
 * @see #afterEach
 * @see #afterAll
 * @see #subSpecification
 * @see #ignoredSubSpecification
 */
public interface CoreDslBuilder {
    /**
     * The core equivalent of {@link SpecBuilder#beginsAll}.
     *
     * @param closure
     *     the body of the callback
     */
    void beforeAll(TestClosure closure);

    /**
     * The core equivalent of {@link SpecBuilder#beginsEach}.
     *
     * @param closure
     *     the body of the callback
     */
    void beforeEach(TestClosure closure);

    /**
     * The core equivalent of {@link SpecBuilder#should}.
     * The only difference is that it doesn't prepend {@code 'should'}
     * to the {@code description}.
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return
     *     an instance of the {@link TestSettings} class
     */
    TestSettings test(String description, TestClosure testBody);

    /**
     * The core equivalent of {@link SpecBuilder#shouldThrow}.
     * The only difference is that it doesn't prepend the text
     * 'should throw &lt;simple name of {@code expectedException}&gt;'
     * to the {@code description}.
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return
     *     a new instance of the {@link ThrowableExpectations} class
     */
    <T extends Throwable> ThrowableExpectations<T> testExpectingException(
            Class<T> expectedException, String description, TestClosure testBody);

    /**
     * The core equivalent of {@link SpecBuilder#fshould}.
     * The only difference is that it doesn't prepend {@code 'should'}
     * to the {@code description}.
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return
     *     an instance of the {@link TestSettings} class
     */
    TestSettings focusedTest(String description, TestClosure testBody);

    /**
     * The core equivalent of {@link SpecBuilder#fshouldThrow}.
     * The only difference is that it doesn't prepend the text
     * 'should throw &lt;simple name of {@code expectedException}&gt;'
     * to the {@code description}.
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return
     *     a new instance of the {@link ThrowableExpectations} class
     */
    <T extends Throwable> ThrowableExpectations<T> focusedTestExpectingException(Class<T> expectedException,
            String description, TestClosure testBody);

    /**
     * The core equivalent of {@link SpecBuilder#xshould}.
     * The only difference is that it doesn't prepend the {@code 'should'}
     * to the {@code description}.
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return
     *     an instance of the {@link TestSettings} class
     */
    TestSettings ignoredTest(String description, TestClosure testBody);

    /**
     * The core equivalent of {@link SpecBuilder#xshouldThrow}.
     * The only difference is that it doesn't prepend the text
     * 'should throw &lt;simple name of {@code expectedException}&gt;'
     * to the {@code description}.
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return
     *     a new instance of the {@link ThrowableExpectations} class
     */
    <T extends Throwable> ThrowableExpectations<T> ignoredTestExpectingException(
            Class<T> expectedException, String description, TestClosure testBody);

    /**
     * The core equivalent of {@link SpecBuilder#endsEach}.
     *
     * @param closure
     *     the body of the callback
     */
    void afterEach(TestClosure closure);

    /**
     * The core equivalent of {@link SpecBuilder#endsAll}.
     *
     * @param closure
     *     the body of the callback
     */
    void afterAll(TestClosure closure);

    /**
     * The core equivalent of {@link SpecBuilder#describes}.
     *
     * @param description
     *     the description of the sub-specification
     * @param specClosure
     *     the definition of the sub-specification
     */
    void subSpecification(String description, Runnable specClosure);

    /**
     * The core equivalent of {@link SpecBuilder#fdescribes}.
     *
     * @param description
     *     the description of the sub-specification
     * @param specClosure
     *     the definition of the sub-specification
     */
    void focusedSubSpecification(String description, Runnable specClosure);

    /**
     * The core equivalent of {@link SpecBuilder#xdescribes}.
     *
     * @param description
     *     the description of the sub-specification
     * @param specClosure
     *     the definition of the sub-specification
     */
    void ignoredSubSpecification(String description, Runnable specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#should(String, TestClosureParams1)}
     * (a parametrized test with a single parameter).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected1} class
     */
    <P> ParamsExpected1<P> parametrizedTest1(String description, TestClosureParams1<P> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fshould(String, TestClosureParams1)}
     * (a parametrized test with a single parameter).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected1} class
     */
    <P> ParamsExpected1<P> focusedParametrizedTest1(String description, TestClosureParams1<P> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xshould(String, TestClosureParams1)}
     * (a parametrized test with a single parameter).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected1} class
     */
    <P> ParamsExpected1<P> ignoredParametrizedTest1(String description, TestClosureParams1<P> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams1)}
     * (a parametrized test with a single parameter).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException1} class
     */
    <T extends Throwable, P> ParamsExpectedException1<T, P> parametrizedTestExpectingException1(
            Class<T> expectedException, String description, TestClosureParams1<P> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fshouldThrow(Class, String, TestClosureParams1)}
     * (a parametrized test with a single parameter).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException1} class
     */
    <T extends Throwable, P> ParamsExpectedException1<T, P> focusedParametrizedTestExpectingException1(
            Class<T> expectedException, String description, TestClosureParams1<P> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xshouldThrow(Class, String, TestClosureParams1)}
     * (a parametrized test with a single parameter).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException1} class
     */
    <T extends Throwable, P> ParamsExpectedException1<T, P> ignoredParametrizedTestExpectingException1(
            Class<T> expectedException, String description, TestClosureParams1<P> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#should(String, TestClosureParams2)}
     * (a parametrized test with 2 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected2} class
     */
    <P1, P2> ParamsExpected2<P1, P2> parametrizedTest2(String description, TestClosureParams2<P1, P2> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fshould(String, TestClosureParams2)}
     * (a parametrized test with 2 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected2} class
     */
    <P1, P2> ParamsExpected2<P1, P2> focusedParametrizedTest2(String description, TestClosureParams2<P1, P2> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xshould(String, TestClosureParams2)}
     * (a parametrized test with 2 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected2} class
     */
    <P1, P2> ParamsExpected2<P1, P2> ignoredParametrizedTest2(String description, TestClosureParams2<P1, P2> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams2)}
     * (a parametrized test with 2 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException2} class
     */
    <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> parametrizedTestExpectingException2(
            Class<T> expectedException, String description, TestClosureParams2<P1, P2> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fshouldThrow(Class, String, TestClosureParams2)}
     * (a parametrized test with 2 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException2} class
     */
    <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> focusedParametrizedTestExpectingException2(
            Class<T> expectedException, String description, TestClosureParams2<P1, P2> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xshouldThrow(Class, String, TestClosureParams2)}
     * (a parametrized test with 2 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException2} class
     */
    <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> ignoredParametrizedTestExpectingException2(
            Class<T> expectedException, String description, TestClosureParams2<P1, P2> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#should(String, TestClosureParams3)}
     * (a parametrized test with 3 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected3} class
     */
    <P1, P2, P3> ParamsExpected3<P1, P2, P3> parametrizedTest3(
            String description, TestClosureParams3<P1, P2, P3> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fshould(String, TestClosureParams3)}
     * (a parametrized test with 3 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected3} class
     */
    <P1, P2, P3> ParamsExpected3<P1, P2, P3> focusedParametrizedTest3(
            String description, TestClosureParams3<P1, P2, P3> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xshould(String, TestClosureParams3)}
     * (a parametrized test with 3 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected3} class
     */
    <P1, P2, P3> ParamsExpected3<P1, P2, P3> ignoredParametrizedTest3(
            String description, TestClosureParams3<P1, P2, P3> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams3)}
     * (a parametrized test with 3 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException3} class
     */
    <T extends Throwable, P1, P2, P3> ParamsExpectedException3<T, P1, P2, P3> parametrizedTestExpectingException3(
            Class<T> expectedException, String description, TestClosureParams3<P1, P2, P3> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fshouldThrow(Class, String, TestClosureParams3)}
     * (a parametrized test with 3 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException3} class
     */
    <T extends Throwable, P1, P2, P3> ParamsExpectedException3<T, P1, P2, P3> focusedParametrizedTestExpectingException3(
            Class<T> expectedException, String description, TestClosureParams3<P1, P2, P3> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xshouldThrow(Class, String, TestClosureParams3)}
     * (a parametrized test with 3 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException3} class
     */
    <T extends Throwable, P1, P2, P3> ParamsExpectedException3<T, P1, P2, P3> ignoredParametrizedTestExpectingException3(
            Class<T> expectedException, String description, TestClosureParams3<P1, P2, P3> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#should(String, TestClosureParams4)}
     * (a parametrized test with 4 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected4} class
     */
    <P1, P2, P3, P4> ParamsExpected4<P1, P2, P3, P4> parametrizedTest4(String description,
            TestClosureParams4<P1, P2, P3, P4> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fshould(String, TestClosureParams4)}
     * (a parametrized test with 4 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected4} class
     */
    <P1, P2, P3, P4> ParamsExpected4<P1, P2, P3, P4> focusedParametrizedTest4(String description,
            TestClosureParams4<P1, P2, P3, P4> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xshould(String, TestClosureParams4)}
     * (a parametrized test with 4 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected4} class
     */
    <P1, P2, P3, P4> ParamsExpected4<P1, P2, P3, P4> ignoredParametrizedTest4(String description,
            TestClosureParams4<P1, P2, P3, P4> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams4)}
     * (a parametrized test with 4 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException4} class
     */
    <T extends Throwable, P1, P2, P3, P4> ParamsExpectedException4<T, P1, P2, P3, P4> parametrizedTestExpectingException4(
            Class<T> expectedException, String description, TestClosureParams4<P1, P2, P3, P4> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fshouldThrow(Class, String, TestClosureParams4)}
     * (a parametrized test with 4 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException4} class
     */
    <T extends Throwable, P1, P2, P3, P4> ParamsExpectedException4<T, P1, P2, P3, P4> focusedParametrizedTestExpectingException4(
            Class<T> expectedException, String description, TestClosureParams4<P1, P2, P3, P4> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xshouldThrow(Class, String, TestClosureParams4)}
     * (a parametrized test with 4 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException4} class
     */
    <T extends Throwable, P1, P2, P3, P4> ParamsExpectedException4<T, P1, P2, P3, P4> ignoredParametrizedTestExpectingException4(
            Class<T> expectedException, String description, TestClosureParams4<P1, P2, P3, P4> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#should(String, TestClosureParams5)}
     * (a parametrized test with 5 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected5} class
     */
    <P1, P2, P3, P4, P5> ParamsExpected5<P1, P2, P3, P4, P5> parametrizedTest5(
            String description, TestClosureParams5<P1, P2, P3, P4, P5> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fshould(String, TestClosureParams5)}
     * (a parametrized test with 5 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected5} class
     */
    <P1, P2, P3, P4, P5> ParamsExpected5<P1, P2, P3, P4, P5> focusedParametrizedTest5(
            String description, TestClosureParams5<P1, P2, P3, P4, P5> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xshould(String, TestClosureParams5)}
     * (a parametrized test with 5 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected5} class
     */
    <P1, P2, P3, P4, P5> ParamsExpected5<P1, P2, P3, P4, P5> ignoredParametrizedTest5(
            String description, TestClosureParams5<P1, P2, P3, P4, P5> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams5)}
     * (a parametrized test with 5 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException5} class
     */
    <T extends Throwable, P1, P2, P3, P4, P5> ParamsExpectedException5<T, P1, P2, P3, P4, P5> parametrizedTestExpectingException5(
            Class<T> expectedException, String description, TestClosureParams5<P1, P2, P3, P4, P5> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fshouldThrow(Class, String, TestClosureParams5)}
     * (a parametrized test with 5 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException5} class
     */
    <T extends Throwable, P1, P2, P3, P4, P5> ParamsExpectedException5<T, P1, P2, P3, P4, P5> focusedParametrizedTestExpectingException5(
            Class<T> expectedException, String description, TestClosureParams5<P1, P2, P3, P4, P5> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xshouldThrow(Class, String, TestClosureParams5)}
     * (a parametrized test with 5 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException5} class
     */
    <T extends Throwable, P1, P2, P3, P4, P5> ParamsExpectedException5<T, P1, P2, P3, P4, P5> ignoredParametrizedTestExpectingException5(
            Class<T> expectedException, String description, TestClosureParams5<P1, P2, P3, P4, P5> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#should(String, TestClosureParams6)}
     * (a parametrized test with 6 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected6} class
     */
    <P1, P2, P3, P4, P5, P6> ParamsExpected6<P1, P2, P3, P4, P5, P6> parametrizedTest6(String description,
            TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fshould(String, TestClosureParams6)}
     * (a parametrized test with 6 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected6} class
     */
    <P1, P2, P3, P4, P5, P6> ParamsExpected6<P1, P2, P3, P4, P5, P6> focusedParametrizedTest6(String description,
            TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xshould(String, TestClosureParams6)}
     * (a parametrized test with 6 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected6} class
     */
    <P1, P2, P3, P4, P5, P6> ParamsExpected6<P1, P2, P3, P4, P5, P6> ignoredParametrizedTest6(String description,
            TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams6)}
     * (a parametrized test with 6 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException6} class
     */
    <T extends Throwable, P1, P2, P3, P4, P5, P6> ParamsExpectedException6<T, P1, P2, P3, P4, P5, P6> parametrizedTestExpectingException6(
            Class<T> expectedException, String description, TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fshouldThrow(Class, String, TestClosureParams6)}
     * (a parametrized test with 6 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException6} class
     */
    <T extends Throwable, P1, P2, P3, P4, P5, P6> ParamsExpectedException6<T, P1, P2, P3, P4, P5, P6> focusedParametrizedTestExpectingException6(
            Class<T> expectedException, String description, TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xshouldThrow(Class, String, TestClosureParams6)}
     * (a parametrized test with 6 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException6} class
     */
    <T extends Throwable, P1, P2, P3, P4, P5, P6> ParamsExpectedException6<T, P1, P2, P3, P4, P5, P6> ignoredParametrizedTestExpectingException6(
            Class<T> expectedException, String description, TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#should(String, TestClosureParams7)}
     * (a parametrized test with 7 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected7} class
     */
    <P1, P2, P3, P4, P5, P6, P7> ParamsExpected7<P1, P2, P3, P4, P5, P6, P7> parametrizedTest7(
            String description, TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fshould(String, TestClosureParams7)}
     * (a parametrized test with 7 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected7} class
     */
    <P1, P2, P3, P4, P5, P6, P7> ParamsExpected7<P1, P2, P3, P4, P5, P6, P7> focusedParametrizedTest7(
            String description, TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xshould(String, TestClosureParams7)}
     * (a parametrized test with 7 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected7} class
     */
    <P1, P2, P3, P4, P5, P6, P7> ParamsExpected7<P1, P2, P3, P4, P5, P6, P7> ignoredParametrizedTest7(
            String description, TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams7)}
     * (a parametrized test with 7 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException7} class
     */
    <T extends Throwable, P1, P2, P3, P4, P5, P6, P7> ParamsExpectedException7<T, P1, P2, P3, P4, P5, P6, P7> parametrizedTestExpectingException7(
            Class<T> expectedException, String description, TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fshouldThrow(Class, String, TestClosureParams7)}
     * (a parametrized test with 7 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException7} class
     */
    <T extends Throwable, P1, P2, P3, P4, P5, P6, P7> ParamsExpectedException7<T, P1, P2, P3, P4, P5, P6, P7> focusedParametrizedTestExpectingException7(
            Class<T> expectedException, String description, TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xshouldThrow(Class, String, TestClosureParams7)}
     * (a parametrized test with 7 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException7} class
     */
    <T extends Throwable, P1, P2, P3, P4, P5, P6, P7> ParamsExpectedException7<T, P1, P2, P3, P4, P5, P6, P7> ignoredParametrizedTestExpectingException7(
            Class<T> expectedException, String description, TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#should(String, TestClosureParams8)}
     * (a parametrized test with 8 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected8} class
     */
    <P1, P2, P3, P4, P5, P6, P7, P8> ParamsExpected8<P1, P2, P3, P4, P5, P6, P7, P8> parametrizedTest8(
            String description, TestClosureParams8<P1, P2, P3, P4, P5, P6, P7, P8> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fshould(String, TestClosureParams8)}
     * (a parametrized test with 8 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected8} class
     */
    <P1, P2, P3, P4, P5, P6, P7, P8> ParamsExpected8<P1, P2, P3, P4, P5, P6, P7, P8> focusedParametrizedTest8(
            String description, TestClosureParams8<P1, P2, P3, P4, P5, P6, P7, P8> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xshould(String, TestClosureParams8)}
     * (a parametrized test with 8 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected8} class
     */
    <P1, P2, P3, P4, P5, P6, P7, P8> ParamsExpected8<P1, P2, P3, P4, P5, P6, P7, P8> ignoredParametrizedTest8(
            String description, TestClosureParams8<P1, P2, P3, P4, P5, P6, P7, P8> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams8)}
     * (a parametrized test with 8 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException8} class
     */
    <T extends Throwable, P1, P2, P3, P4, P5, P6, P7, P8> ParamsExpectedException8<T, P1, P2, P3, P4, P5, P6, P7, P8> parametrizedTestExpectingException8(
            Class<T> expectedException, String description, TestClosureParams8<P1, P2, P3, P4, P5, P6, P7, P8> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fshouldThrow(Class, String, TestClosureParams8)}
     * (a parametrized test with 8 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException8} class
     */
    <T extends Throwable, P1, P2, P3, P4, P5, P6, P7, P8> ParamsExpectedException8<T, P1, P2, P3, P4, P5, P6, P7, P8> focusedParametrizedTestExpectingException8(
            Class<T> expectedException, String description, TestClosureParams8<P1, P2, P3, P4, P5, P6, P7, P8> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xshouldThrow(Class, String, TestClosureParams8)}
     * (a parametrized test with 8 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException8} class
     */
    <T extends Throwable, P1, P2, P3, P4, P5, P6, P7, P8> ParamsExpectedException8<T, P1, P2, P3, P4, P5, P6, P7, P8> ignoredParametrizedTestExpectingException8(
            Class<T> expectedException, String description, TestClosureParams8<P1, P2, P3, P4, P5, P6, P7, P8> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#should(String, TestClosureParams9)}
     * (a parametrized test with 9 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected9} class
     */
    <P1, P2, P3, P4, P5, P6, P7, P8, P9> ParamsExpected9<P1, P2, P3, P4, P5, P6, P7, P8, P9> parametrizedTest9(
            String description, TestClosureParams9<P1, P2, P3, P4, P5, P6, P7, P8, P9> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fshould(String, TestClosureParams9)}
     * (a parametrized test with 9 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected9} class
     */
    <P1, P2, P3, P4, P5, P6, P7, P8, P9> ParamsExpected9<P1, P2, P3, P4, P5, P6, P7, P8, P9> focusedParametrizedTest9(
            String description, TestClosureParams9<P1, P2, P3, P4, P5, P6, P7, P8, P9> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xshould(String, TestClosureParams9)}
     * (a parametrized test with 9 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected9} class
     */
    <P1, P2, P3, P4, P5, P6, P7, P8, P9> ParamsExpected9<P1, P2, P3, P4, P5, P6, P7, P8, P9> ignoredParametrizedTest9(
            String description, TestClosureParams9<P1, P2, P3, P4, P5, P6, P7, P8, P9> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#shouldThrow(Class, String, TestClosureParams9)}
     * (a parametrized test with 9 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException9} class
     */
    <T extends Throwable, P1, P2, P3, P4, P5, P6, P7, P8, P9> ParamsExpectedException9<T, P1, P2, P3, P4, P5, P6, P7, P8, P9> parametrizedTestExpectingException9(
            Class<T> expectedException, String description, TestClosureParams9<P1, P2, P3, P4, P5, P6, P7, P8, P9> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fshouldThrow(Class, String, TestClosureParams9)}
     * (a parametrized test with 9 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException9} class
     */
    <T extends Throwable, P1, P2, P3, P4, P5, P6, P7, P8, P9> ParamsExpectedException9<T, P1, P2, P3, P4, P5, P6, P7, P8, P9> focusedParametrizedTestExpectingException9(
            Class<T> expectedException, String description, TestClosureParams9<P1, P2, P3, P4, P5, P6, P7, P8, P9> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xshouldThrow(Class, String, TestClosureParams9)}
     * (a parametrized test with 9 parameters).
     *
     * @param expectedException
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpectedException9} class
     */
    <T extends Throwable, P1, P2, P3, P4, P5, P6, P7, P8, P9> ParamsExpectedException9<T, P1, P2, P3, P4, P5, P6, P7, P8, P9> ignoredParametrizedTestExpectingException9(
            Class<T> expectedException, String description, TestClosureParams9<P1, P2, P3, P4, P5, P6, P7, P8, P9> testBody);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#describes(String, RunnableParams1)}
     * (a parametrized sub-specification with a single parameter).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup1} class
     */
    <P> ParamsExpectedSubgroup1<P> parametrizedSubSpecification1(String description,
            RunnableParams1<P> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fdescribes(String, RunnableParams1)}
     * (a parametrized sub-specification with a single parameter).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup1} class
     */
    <P> ParamsExpectedSubgroup1<P> focusedParametrizedSubSpecification1(String description,
            RunnableParams1<P> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xdescribes(String, RunnableParams1)}
     * (a parametrized sub-specification with a single parameter).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup1} class
     */
    <P> ParamsExpectedSubgroup1<P> ignoredParametrizedSubSpecification1(String description,
            RunnableParams1<P> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#describes(String, RunnableParams2)}
     * (a parametrized sub-specification with 2 parameters).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup2} class
     */
    <P1, P2> ParamsExpectedSubgroup2<P1, P2> parametrizedSubSpecification2(String description,
            RunnableParams2<P1, P2> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fdescribes(String, RunnableParams2)}
     * (a parametrized sub-specification with 2 parameters).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup2} class
     */
    <P1, P2> ParamsExpectedSubgroup2<P1, P2> focusedParametrizedSubSpecification2(String description,
            RunnableParams2<P1, P2> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xdescribes(String, RunnableParams2)}
     * (a parametrized sub-specification with 2 parameters).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup2} class
     */
    <P1, P2> ParamsExpectedSubgroup2<P1, P2> ignoredParametrizedSubSpecification2(String description,
            RunnableParams2<P1, P2> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#describes(String, RunnableParams3)}
     * (a parametrized sub-specification with 3 parameters).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup3} class
     */
    <P1, P2, P3> ParamsExpectedSubgroup3<P1, P2, P3> parametrizedSubSpecification3(String description,
            RunnableParams3<P1, P2, P3> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fdescribes(String, RunnableParams3)}
     * (a parametrized sub-specification with 3 parameters).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup3} class
     */
    <P1, P2, P3> ParamsExpectedSubgroup3<P1, P2, P3> focusedParametrizedSubSpecification3(String description,
            RunnableParams3<P1, P2, P3> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xdescribes(String, RunnableParams3)}
     * (a parametrized sub-specification with 3 parameters).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup3} class
     */
    <P1, P2, P3> ParamsExpectedSubgroup3<P1, P2, P3> ignoredParametrizedSubSpecification3(String description,
            RunnableParams3<P1, P2, P3> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#describes(String, RunnableParams4)}
     * (a parametrized sub-specification with 4 parameters).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup4} class
     */
    <P1, P2, P3, P4> ParamsExpectedSubgroup4<P1, P2, P3, P4> parametrizedSubSpecification4(String description,
            RunnableParams4<P1, P2, P3, P4> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fdescribes(String, RunnableParams4)}
     * (a parametrized sub-specification with 4 parameters).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup4} class
     */
    <P1, P2, P3, P4> ParamsExpectedSubgroup4<P1, P2, P3, P4> focusedParametrizedSubSpecification4(String description,
            RunnableParams4<P1, P2, P3, P4> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xdescribes(String, RunnableParams4)}
     * (a parametrized sub-specification with 4 parameters).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup4} class
     */
    <P1, P2, P3, P4> ParamsExpectedSubgroup4<P1, P2, P3, P4> ignoredParametrizedSubSpecification4(String description,
            RunnableParams4<P1, P2, P3, P4> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#describes(String, RunnableParams5)}
     * (a parametrized sub-specification with 5 parameters).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup5} class
     */
    <P1, P2, P3, P4, P5> ParamsExpectedSubgroup5<P1, P2, P3, P4, P5> parametrizedSubSpecification5(
            String description, RunnableParams5<P1, P2, P3, P4, P5> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fdescribes(String, RunnableParams5)}
     * (a parametrized sub-specification with 5 parameters).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup5} class
     */
    <P1, P2, P3, P4, P5> ParamsExpectedSubgroup5<P1, P2, P3, P4, P5> focusedParametrizedSubSpecification5(
            String description, RunnableParams5<P1, P2, P3, P4, P5> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xdescribes(String, RunnableParams5)}
     * (a parametrized sub-specification with 5 parameters).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup5} class
     */
    <P1, P2, P3, P4, P5> ParamsExpectedSubgroup5<P1, P2, P3, P4, P5> ignoredParametrizedSubSpecification5(
            String description, RunnableParams5<P1, P2, P3, P4, P5> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#describes(String, RunnableParams6)}
     * (a parametrized sub-specification with 6 parameters).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup6} class
     */
    <P1, P2, P3, P4, P5, P6> ParamsExpectedSubgroup6<P1, P2, P3, P4, P5, P6> parametrizedSubSpecification6(
            String description, RunnableParams6<P1, P2, P3, P4, P5, P6> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fdescribes(String, RunnableParams6)}
     * (a parametrized sub-specification with 6 parameters).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup6} class
     */
    <P1, P2, P3, P4, P5, P6> ParamsExpectedSubgroup6<P1, P2, P3, P4, P5, P6> focusedParametrizedSubSpecification6(
            String description, RunnableParams6<P1, P2, P3, P4, P5, P6> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xdescribes(String, RunnableParams6)}
     * (a parametrized sub-specification with 6 parameters).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup6} class
     */
    <P1, P2, P3, P4, P5, P6> ParamsExpectedSubgroup6<P1, P2, P3, P4, P5, P6> ignoredParametrizedSubSpecification6(
            String description, RunnableParams6<P1, P2, P3, P4, P5, P6> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#describes(String, RunnableParams7)}
     * (a parametrized sub-specification with 7 parameters).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup7} class
     */
    <P1, P2, P3, P4, P5, P6, P7> ParamsExpectedSubgroup7<P1, P2, P3, P4, P5, P6, P7> parametrizedSubSpecification7(
            String description, RunnableParams7<P1, P2, P3, P4, P5, P6, P7> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#fdescribes(String, RunnableParams7)}
     * (a parametrized sub-specification with 7 parameters).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup7} class
     */
    <P1, P2, P3, P4, P5, P6, P7> ParamsExpectedSubgroup7<P1, P2, P3, P4, P5, P6, P7> focusedParametrizedSubSpecification7(
            String description, RunnableParams7<P1, P2, P3, P4, P5, P6, P7> specClosure);

    /**
     * The core equivalent of {@link ParamsSpecBuilder#xdescribes(String, RunnableParams7)}
     * (a parametrized sub-specification with 7 parameters).
     *
     * @param description
     *     the description of the new test group
     * @param specClosure
     *     the callback used to define the new test group
     * @return an instance of the {@link ParamsExpectedSubgroup7} class
     */
    <P1, P2, P3, P4, P5, P6, P7> ParamsExpectedSubgroup7<P1, P2, P3, P4, P5, P6, P7> ignoredParametrizedSubSpecification7(
            String description, RunnableParams7<P1, P2, P3, P4, P5, P6, P7> specClosure);
}
