package org.specnaz;

import org.specnaz.utils.TestClosure;

/**
 * The class used to create the specification.
 * An instance of this interface is passed to the
 * {@link Specnaz#describes} method to build the
 * test plan, and then later execute it.
 *
 * @see #should
 * @see #shouldThrow
 * @see #beginsEach
 * @see #beginsAll
 * @see #endsEach
 * @see #endsAll
 * @see #describes
 */
public interface SpecBuilder {
    /**
     * A lifecycle callback executed only once for each test group.
     * The equivalent of RSpec's or Jasmine's {@code beforeAll}.
     * <p>
     * You can have any number of them in each group,
     * and they are guaranteed to run in the order they were declared.
     * <p>
     * Nested groups execute all of their ancestor's {@code beginsAll} callbacks.
     * They are executed in "outside-in" order -
     * meaning, parent ones run before the child's.
     *
     * @param closure
     *     the body of the callback
     *
     * @see #should
     * @see #shouldThrow
     * @see #beginsEach
     * @see #endsEach
     * @see #endsAll
     * @see #describes
     */
    void beginsAll(TestClosure closure);

    /**
     * A lifecycle callback executed before each test case.
     * The equivalent of RSpec's or Jasmine's {@code beforeEach}.
     * <p>
     * You can have any number of them in each group,
     * and they are guaranteed to run in the order they were declared.
     * <p>
     * Nested groups execute all of their ancestor's {@code beginsEach} callbacks.
     * They are executed in "outside-in" order -
     * meaning, parent ones run before the child's.
     *
     * @param closure
     *     the body of the callback
     *
     * @see #should
     * @see #shouldThrow
     * @see #beginsAll
     * @see #endsEach
     * @see #endsAll
     * @see #describes
     */
    void beginsEach(TestClosure closure);

    /**
     * Defines a test.
     * <p>
     * You can declare any number of them in each group.
     *
     * @param description
     *     the description of this test.
     *     It will serve as the name for this test in the reports.
     *     Note that the word 'should' will be prepended to this description
     *     by the library
     * @param testBody
     *     the body of the test case
     *
     * @see #shouldThrow
     * @see #beginsEach
     * @see #beginsAll
     * @see #endsEach
     * @see #endsAll
     * @see #describes
     */
    void should(String description, TestClosure testBody);

    /**
     * Define a test expecting an Exception to be thrown.
     * <p>
     * This is very similar to the {@link #should} method, except the test passes
     * only if it results in an Exception (of type {@code expectedException})
     * being thrown, similarly to JUnit's {@code @Test.expected}.
     *
     * @param expectedException
     *     the type of Exception expected from the test.
     *     The Exception resulting from executing the test must be of this type
     *     (so, be an instance of either the {@code expectedException} class,
     *     or a subclass of {@code expectedException})
     *     for the test to have been deemed passing
     * @param description
     *     the description of this test.
     *     It will serve as the name for this test in the reports.
     *     Note that the words 'should throw &lt;ExpectedExceptionClass&gt;'
     *     will be prepended to it by the library
     * @param testBody
     *     the body of the test case
     *
     * @see #should
     * @see #beginsEach
     * @see #beginsAll
     * @see #endsEach
     * @see #endsAll
     * @see #describes
     */
    void shouldThrow(Class<? extends Throwable> expectedException,
                     String description, TestClosure testBody);


    /**
     * Define a test expecting an Exception to be thrown with a specific message.
     * <p>
     * This is very similar to the {@link #should} method, except the test passes
     * only if it results in an Exception (of type {@code expectedException})
     * being thrown with a specific message in it, similarly to JUnit's {@code @Test.expected}.
     *
     * @param expectedException
     *     the type of Exception expected from the test.
     *     The Exception resulting from executing the test must be of this type
     *     (so, be an instance of either the {@code expectedException} class,
     *     or a subclass of {@code expectedException})
     *     for the test to have been deemed passing
     * @param expectedMessage
     *     the message that should be part of the exception.
     *     Important when we have different parts of the code when we are throwing the
     *     same exception and we want to discriminate by message.
     * @param description
     *     the description of this test.
     *     It will serve as the name for this test in the reports.
     *     Note that the words 'should throw &lt;ExpectedExceptionClass&gt;'
     *     will be prepended to it by the library
     * @param testBody
     *     the body of the test case
     *
     * @see #should
     * @see #beginsEach
     * @see #beginsAll
     * @see #endsEach
     * @see #endsAll
     * @see #describes
     */
    void shouldThrowWithMessage(Class<? extends Throwable> expectedException,
                                String expectedMessage,
                                String description, TestClosure testBody);

    /**
     * A lifecycle callback executed after each test case.
     * The equivalent of RSpec's or Jasmine's {@code afterEach}.
     * <p>
     * You can have any number of them in each group,
     * and they are guaranteed to run in the order they were declared.
     * <p>
     * Nested groups execute all of their ancestor's {@code endsEach} callbacks.
     * They are executed in "inside-out" order -
     * meaning, child ones run before the parent's
     * (in opposite order than the {@code beforeEach} ones).
     *
     * @param closure
     *     the body of the callback
     *
     * @see #should
     * @see #shouldThrow
     * @see #beginsEach
     * @see #beginsAll
     * @see #endsAll
     * @see #describes
     */
    void endsEach(TestClosure closure);

    /**
     * A lifecycle callback executed once after the test group it was declared in.
     * The equivalent of RSpec's or Jasmine's {@code afterAll}.
     * <p>
     * You can have any number of them in each group,
     * and they are guaranteed to run in the order they were declared.
     * <p>
     * Nested groups execute all of their ancestor's {@code endsAll} callbacks.
     * They are executed in "inside-out" order -
     * meaning, child ones run before the parent's
     * (in opposite order than the {@code beforeAll} ones).
     *
     * @param closure
     *     the body of the callback
     *
     * @see #should
     * @see #shouldThrow
     * @see #beginsEach
     * @see #beginsAll
     * @see #endsEach
     * @see #describes
     */
    void endsAll(TestClosure closure);

    /**
     * Creates a subgroup of tests, with the current group as it's parent -
     * a specification within the specification.
     * The subgroup can use all of the same methods
     * ({@code begins/ends/should/describes}) that the parent group has.
     * <p>
     * You can have as many {@code describes} in a test group as you want,
     * and you can nest them arbitrarily deep.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group,
     *    similarly to how {@link Specnaz#describes} defines the
     *    top-level group
     *
     * @see #should
     * @see #shouldThrow
     * @see #beginsEach
     * @see #beginsAll
     * @see #endsEach
     * @see #endsAll
     */
    void describes(String description, Runnable specClosure);
}
