package org.specnaz.core;

import org.specnaz.SpecBuilder;
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
     */
    void test(String description, TestClosure testBody);

    /**
     * The core equivalent of {@link SpecBuilder#shouldThrow}.
     * The only difference is that it doesn't prepend the text
     * 'should throw &lt;simple name of {@code exceptionClass}&gt;'
     * to the {@code description}.
     *
     * @param exceptionClass
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return
     *     a new instance of the {@link ThrowableExpectations} class
     */
    ThrowableExpectations testExpectingException(Class<? extends Throwable> exceptionClass,
                                                 String description, TestClosure testBody);

    /**
     * The core equivalent of {@link SpecBuilder#fshould}.
     * The only difference is that it doesn't prepend {@code 'should'}
     * to the {@code description}.
     *
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     */
    void focusedTest(String description, TestClosure testBody);

    /**
     * The core equivalent of {@link SpecBuilder#fshouldThrow}.
     * The only difference is that it doesn't prepend the text
     * 'should throw &lt;simple name of {@code exceptionClass}&gt;'
     * to the {@code description}.
     *
     * @param exceptionClass
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return
     *     a new instance of the {@link ThrowableExpectations} class
     */
    ThrowableExpectations focusedTestExpectingException(Class<? extends Throwable> exceptionClass,
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
     */
    void ignoredTest(String description, TestClosure testBody);

    /**
     * The core equivalent of {@link SpecBuilder#xshouldThrow}.
     * The only difference is that it doesn't prepend the text
     * 'should throw &lt;simple name of {@code exceptionClass}&gt;'
     * to the {@code description}.
     *
     * @param exceptionClass
     *     the class of the Exception we expect in this test
     * @param description
     *     the description of the test case
     * @param testBody
     *     the body of the test case
     * @return
     *     a new instance of the {@link ThrowableExpectations} class
     */
    ThrowableExpectations ignoredTestExpectingException(Class<? extends Throwable> exceptionClass,
                                                        String description, TestClosure testBody);

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
}
