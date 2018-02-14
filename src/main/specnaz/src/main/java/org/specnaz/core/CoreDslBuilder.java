package org.specnaz.core;

import org.specnaz.SpecBuilder;
import org.specnaz.TestSettings;
import org.specnaz.params.ParamsExpected1;
import org.specnaz.params.ParamsExpected2;
import org.specnaz.params.ParamsExpectedException1;
import org.specnaz.params.ParamsExpectedException2;
import org.specnaz.params.ParamsSpecBuilder;
import org.specnaz.params.TestClosureParams1;
import org.specnaz.params.TestClosureParams2;
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
     * The core equivalent of {@link ParamsSpecBuilder#fshould(String, TestClosureParams2)}
     * (a parametrized test with 2 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected2} class
     */
    <P1, P2> ParamsExpected2<P1,P2> focusedParametrizedTest2(String description, TestClosureParams2<P1, P2> testBody);

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
     * The core equivalent of {@link ParamsSpecBuilder#xshould(String, TestClosureParams2)}
     * (a parametrized test with 2 parameters).
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return an instance of the {@link ParamsExpected2} class
     */
    <P1, P2> ParamsExpected2<P1,P2> ignoredParametrizedTest2(String description, TestClosureParams2<P1, P2> testBody);

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
     * @return an instance of the {@link ParamsExpected2} class
     */
    <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> ignoredParametrizedTestExpectingException2(
            Class<T> expectedException, String description, TestClosureParams2<P1, P2> testBody);
}
