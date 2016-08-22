package org.specnaz.utils;

/**
 * A version of the {@link Box} class for primitive {@code float} values.
 * For code readability, its constructor is private;
 * you create instances using the {@link FloatBox#boxWith(float value)} static method.
 *
 * @see Box
 */
public final class FloatBox {
    /**
     * Creates a new {@link FloatBox} wrapping the provided value.
     *
     * @param value
     *     the value to wrap in a box
     * @return
     *     a new instance of the {@link FloatBox} class with the {@link FloatBox#$} field set to {@code value}
     */
    public static FloatBox boxWith(float value) {
        return new FloatBox(value);
    }

    /**
     * The value wrapped in the box.
     * It's {@code public} by design, so accessing and modifying it is as concise as possible.
     */
    public float $;

    private FloatBox(float value) {
        this.$ = value;
    }
}
