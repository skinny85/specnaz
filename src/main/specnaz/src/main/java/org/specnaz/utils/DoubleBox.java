package org.specnaz.utils;

/**
 * A version of the {@link Box} class for primitive {@code double} values.
 * For code readability, its constructor is private;
 * you create instances using the {@link DoubleBox#boxWith(double value)} static method.
 *
 * @see Box
 */
public final class DoubleBox {
    /**
     * Creates a new {@link DoubleBox} wrapping the provided value.
     *
     * @param value
     *     the value to wrap in a box
     * @return
     *     a new instance of the {@link DoubleBox} class with the {@link DoubleBox#$} field set to {@code value}
     */
    public static DoubleBox boxWith(double value) {
        return new DoubleBox(value);
    }

    /**
     * The value wrapped in the box.
     * It's {@code public} by design, so accessing and modifying it is as concise as possible.
     */
    public double $;

    private DoubleBox(double value) {
        this.$ = value;
    }
}
