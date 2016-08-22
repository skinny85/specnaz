package org.specnaz.utils;

/**
 * A version of the {@link Box} class for primitive {@code short} values.
 * For code readability, its constructor is private;
 * you create instances using the {@link ShortBox#boxWith(short value)} static method.
 *
 * @see Box
 */
public final class ShortBox {
    /**
     * Creates a new {@link ShortBox} wrapping the provided value.
     *
     * @param value
     *     the value to wrap in a box
     * @return
     *     a new instance of the {@link ShortBox} class with the {@link ShortBox#$} field set to {@code value}
     */
    public static ShortBox boxWith(short value) {
        return new ShortBox(value);
    }

    /**
     * The value wrapped in the box.
     * It's {@code public} by design, so accessing and modifying it is as concise as possible.
     */
    public short $;

    private ShortBox(short value) {
        this.$ = value;
    }
}
