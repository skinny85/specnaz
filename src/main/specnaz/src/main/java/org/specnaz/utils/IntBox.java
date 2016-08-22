package org.specnaz.utils;

/**
 * A version of the {@link Box} class for primitive {@code int} values.
 * For code readability, its constructor is private;
 * you create instances using the {@link IntBox#boxWith(int value)} static method.
 *
 * @see Box
 */
public final class IntBox {
    /**
     * Creates a new {@link IntBox} wrapping the provided value.
     *
     * @param value
     *     the value to wrap in a box
     * @return
     *     a new instance of the {@link IntBox} class with the {@link IntBox#$} field set to {@code value}
     */
    public static IntBox boxWith(int value) {
        return new IntBox(value);
    }

    /**
     * The value wrapped in the box.
     * It's {@code public} by design, so accessing and modifying it is as concise as possible.
     */
    public int $;

    private IntBox(int value) {
        this.$ = value;
    }
}
