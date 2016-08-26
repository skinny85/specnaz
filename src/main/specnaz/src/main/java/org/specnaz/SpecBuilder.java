package org.specnaz;

import org.specnaz.utils.TestClosure;

import java.util.function.Consumer;

/**
 * The class used to create the specification.
 * An instance of this interface is passed to the
 * {@link Specnaz#describes} method to build the
 * test plan, and then later execute it.
 *
 * @see SpecBuilder#should
 * @see SpecBuilder#beginsEach
 * @see SpecBuilder#beginsAll
 * @see SpecBuilder#endsEach
 * @see SpecBuilder#endsAll
 * @see SpecBuilder#describes
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
     * @see SpecBuilder#should
     * @see SpecBuilder#beginsEach
     * @see SpecBuilder#endsEach
     * @see SpecBuilder#endsAll
     * @see SpecBuilder#describes
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
     * @see SpecBuilder#should
     * @see SpecBuilder#beginsAll
     * @see SpecBuilder#endsEach
     * @see SpecBuilder#endsAll
     * @see SpecBuilder#describes
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
     * @see SpecBuilder#beginsEach
     * @see SpecBuilder#beginsAll
     * @see SpecBuilder#endsEach
     * @see SpecBuilder#endsAll
     * @see SpecBuilder#describes
     */
    void should(String description, TestClosure testBody);

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
     * @see SpecBuilder#should
     * @see SpecBuilder#beginsEach
     * @see SpecBuilder#beginsAll
     * @see SpecBuilder#endsAll
     * @see SpecBuilder#describes
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
     * @see SpecBuilder#should
     * @see SpecBuilder#beginsEach
     * @see SpecBuilder#beginsAll
     * @see SpecBuilder#endsEach
     * @see SpecBuilder#describes
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
     * @see SpecBuilder#should
     * @see SpecBuilder#beginsEach
     * @see SpecBuilder#beginsAll
     * @see SpecBuilder#endsEach
     * @see SpecBuilder#endsAll
     */
    void describes(String description, Runnable specClosure);
}
