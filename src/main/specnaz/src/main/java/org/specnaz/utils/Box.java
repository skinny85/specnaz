package org.specnaz.utils;

import static java.util.Objects.requireNonNull;

/**
 * A simple utility class providing a wrapper around a single value,
 * available through a public field named {@link Box#$}.
 * It's purpose is to get around Java's <i>variables referenced in lambda expressions must
 * be final or effectively final</i> limitation.
 *
 * <p>
 * For example, we often want to write code like this:
 * </p>
 *
 * <pre>
 * {@code
 * describes("A List", it -> {
 *         List<Int> list;
 *
 *         it.beginsEach(() -> {
 *             // we reset the list before each test
 *             list = new ArrayList<>();
 *         });
 * });
 * }
 * </pre>
 *
 * However, that is not valid Java!
 *
 * <p>
 * With {@code Box}, it's easy:
 * </p>
 *
 * <pre>
 * {@code
 * describes("A List", it -> {
 *         Box<List<Int>> list = emptyBox();
 *
 *         it.beginsEach(() -> {
 *             // we reset the list before each test
 *             list.$ = new ArrayList<>();
 *         });
 *
 *         // use list.$ where you would previously use just list
 * });
 * }
 * </pre>
 *
 * To make code read a little better
 * (and use Java's type inference to the maximum),
 * {@code Box} has a private constructor, and provides two static methods
 * to create instances of it: {@link Box#emptyBox()} and {@link Box#boxWith(T thing)}.
 * <p>
 * There are also equivalent Box classes for primitives:
 * {@link IntBox}, {@link BoolBox} etc.
 *
 * @param <T>
 *     the type of the wrapped value. Can be any type
 * @see IntBox
 * @see BoolBox
 * @see LongBox
 * @see FloatBox
 * @see DoubleBox
 * @see CharBox
 * @see ShortBox
 */
public final class Box<T> {
    /**
     * Creates an new, empty
     * (that is, with the value set to {@code null})
     * {@link Box}.
     *
     * @param <T>
     *     the type of the wrapped value. Can be any type
     * @return
     *     a new instance of the {@link Box} class with the {@link Box#$} field set to {@code null}
     */
    public static <T> Box<T> emptyBox() {
        return new Box<>(null);
    }

    /**
     * Creates a new {@link Box} with the value set to {@code thing}.
     * Note that {@code thing} must not be {@code null} -
     * if you want to create a {@link Box} with the value set to {@code null},
     * use {@link Box#emptyBox}.
     *
     * @param thing
     *     the object to wrap in a box
     * @param <T>
     *     the type of {@code thing}. Can be any type
     * @return
     *     a new instance of the {@link Box} class with the {@link Box#$} field set to {@code thing}
     * @throws NullPointerException
     *     if {@code thing} is {@code null}
     */
    public static <T> Box<T> boxWith(T thing) throws NullPointerException {
        return new Box<>(requireNonNull(thing));
    }

    /**
     * The value wrapped in the box.
     * It's {@code public} by design, so accessing and modifying it is as concise as possible.
     */
    public T $;

    private Box(T thing) {
        this.$ = thing;
    }
}
