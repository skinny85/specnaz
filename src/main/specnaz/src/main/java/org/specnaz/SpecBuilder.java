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
 * @see #fshould
 * @see #fshouldThrow
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
     * @see #fshould
     * @see #fshouldThrow
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
     * @see #fshould
     * @see #fshouldThrow
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
     * @see #fshould
     * @see #fshouldThrow
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
     * @see #fshould
     * @see #fshouldThrow
     */
    void shouldThrow(Class<? extends Throwable> expectedException,
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
     * @see #fshould
     * @see #fshouldThrow
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
     * @see #fshould
     * @see #fshouldThrow
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
     * @see #fshould
     * @see #fshouldThrow
     */
    void describes(String description, Runnable specClosure);

    /**
     * Used to temporarily mark a {@link #should} test as 'focused'.
     * <p>
     * This feature works the same as in <a href="http://rspec.info/">RSpec</a>
     * or <a href="http://jasmine.github.io/">Jasmine</a>.
     * If a class contains at least one focused test,
     * then only focused tests will be ran when it is executed -
     * unfocused (that is, created with {@link #should}) tests will be ignored.
     * This is useful when wanting to run and debug a single test in a class -
     * it's easy to run a single test when using 'vanilla' JUnit, but quite hard with Specnaz
     * (the IDEs and build tools were not really designed for tree-based tests).
     * With this method, you can simply add an 'f' in front of a call to {@link #should},
     * and the next time this spec class is ran,
     * only the {@code fshould} tests will actually be executed.
     * <p>
     * Naturally, all of the fixtures ({@code beginsAll/Each} and
     * {@code endsAll/Each}) in the tree will be executed,
     * just like for regular, 'unfocused' tests -
     * including fixtures from parent groups whose tests were all ignored
     * because of not being focused.
     *
     * @param description
     *     the description of this test.
     *     It will serve as the name for this test in the reports.
     *     Note that the word 'should' will be prepended to this description
     *     by the library
     * @param testBody
     *     the body of the test case
     *
     * @deprecated
     *     This method is deprecated, as it's only meant as a temporary stop
     *     gap to aid you in debugging a failing test - it's not meant to be
     *     part of the test suite permanently. Deprecating it means
     *     there is a higher chance you notice it, and remember to remove the 'f'
     *     at the beginning before committing the change to source control.
     *
     * @see #should
     * @see #shouldThrow
     * @see #beginsEach
     * @see #beginsAll
     * @see #endsEach
     * @see #endsAll
     * @see #describes
     * @see #fshouldThrow
     */
    @Deprecated
    void fshould(String description, TestClosure testBody);

    /**
     * The 'focused' equivalent of {@link #shouldThrow}.
     * See the {@link #fshould} documentation for a description of
     * what it means to be 'focused'.
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
     * @deprecated
     *     This method is deprecated for exactly the same reasons {@link #fshould} is.
     *
     * @see #should
     * @see #shouldThrow
     * @see #beginsEach
     * @see #beginsAll
     * @see #endsEach
     * @see #endsAll
     * @see #describes
     * @see #fshould
     */
    @Deprecated
    void fshouldThrow(Class<? extends Throwable> expectedException,
                      String description, TestClosure testBody);
}
