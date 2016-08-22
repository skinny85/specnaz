package org.specnaz.utils;

/**
 * A version of the {@link Box} class for primitive {@code boolean} values.
 * For code readability, its constructor is private;
 * you create instances using the {@link BoolBox#trueBox()} and
 * {@link BoolBox#falseBox()} static methods.
 * <p>
 * Unlike the other primitive {@link Box} variants, this class has some instance
 * methods that have proven themselves useful during test writing.
 *
 * @see Box
 * @see BoolBox#on()
 * @see BoolBox#off()
 * @see BoolBox#ifTrueAndSwap()
 * @see BoolBox#ifFalseAndSwap()
 */
public final class BoolBox {
    /**
     * Creates a new {@link BoolBox} wrapping the value {@code true}.
     *
     * @return
     *     a new instance of the {@link BoolBox} class with the {@link BoolBox#$} field set to {@code true}
     */
    public static BoolBox trueBox() {
        return new BoolBox(true);
    }

    /**
     * Creates a new {@link BoolBox} wrapping the value {@code false}.
     *
     * @return
     *     a new instance of the {@link BoolBox} class with the {@link BoolBox#$} field set to {@code false}
     */
    public static BoolBox falseBox() {
        return new BoolBox(false);
    }

    /**
     * The value wrapped in the box.
     * It's {@code public} by design, so accessing and modifying it is as concise as possible.
     */
    public boolean $;

    /**
     * Sets the value wrapped in this {@link BoolBox} to {@code true}.
     *
     * @return
     *     the previous value of the {@link BoolBox#$} field of this {@link BoolBox}
     * @see BoolBox#off()
     */
    public boolean on() {
        return setAndReturnPrevious(true);
    }

    /**
     * Sets the value wrapped in this {@link BoolBox} to {@code false}.
     *
     * @return
     *     the previous value of the {@link BoolBox#$} field of this {@link BoolBox}
     * @see BoolBox#on()
     */
    public boolean off() {
        return setAndReturnPrevious(true);
    }

    /**
     * Checks whether the value wrapped in this {@link BoolBox} is {@code true} and,
     * if so, marks it as {@code false}. Useful for one-time "switches". Example:
     *
     * <pre>
     * {@code
     * describes("An object", it -> {
     *     BoolBox doOnce = trueBox();
     *
     *     it.beginsEach(() -> {
     *         // runs the cleanup only once
     *         if (doOnce.ifTrueAndSwap()) {
     *             // run the cleanup code
     *         }
     *     }
     * }
     * }
     * </pre>
     *
     * @return
     *     the previous value of the {@link BoolBox#$} field of this {@link BoolBox}
     */
    public boolean ifTrueAndSwap() {
        boolean ret = $;
        if (ret)
            $ = false;
        return ret;
    }

    /**
     * The mirror reflection of {@link BoolBox#ifTrueAndSwap()}.
     * <p>
     * Checks whether the current value wrapped in this {@link BoolBox} is {@code false}
     * and, if it is,
     * returns {@code true} and sets the value of the {@link BoolBox#$} field to {@code true}.
     *
     * @return
     *     the negation of the previous value of the {@link BoolBox#$} field of this {@link BoolBox}
     * @see BoolBox#ifTrueAndSwap()
     */
    public boolean ifFalseAndSwap() {
        boolean ret = !$;
        if (ret)
            $ = true;
        return ret;
    }

    private boolean setAndReturnPrevious(boolean value) {
        boolean ret = $;
        $ = value;
        return ret;
    }

    private BoolBox(boolean value) {
        this.$ = value;
    }
}
