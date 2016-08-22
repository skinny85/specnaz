package org.specnaz.utils;

/**
 * A version of the {@link Box} class for primitive {@code char} values.
 * For code readability, its constructor is private;
 * you create instances using the {@link CharBox#boxWith(char value)} static method.
 *
 * @see Box
 */
public final class CharBox {
    /**
     * Creates a new {@link CharBox} wrapping the provided value.
     *
     * @param value
     *     the value to wrap in a box
     * @return
     *     a new instance of the {@link CharBox} class with the {@link CharBox#$} field set to {@code value}
     */
    public static CharBox boxWith(char value) {
        return new CharBox(value);
    }

    /**
     * The value wrapped in the box.
     * It's {@code public} by design, so accessing and modifying it is as concise as possible.
     */
    public char $;

    private CharBox(char value) {
        this.$ = value;
    }
}
