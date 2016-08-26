package org.specnaz.utils;

/**
 * A version of the {@link Box} class for primitive {@code byte} values.
 * For code readability, its constructor is private;
 * you create instances using the {@link ByteBox#boxWith(byte value)} static method.
 *
 * @see Box
 */
public final class ByteBox {
    /**
     * Creates a new {@link ByteBox} wrapping the provided value.
     *
     * @param value
     *     the value to wrap in a box
     * @return
     *     a new instance of the {@link ByteBox} class with the {@link ByteBox#$} field set to {@code value}
     */
    public static ByteBox boxWith(byte value) {
        return new ByteBox(value);
    }

    /**
     * The value wrapped in the box.
     * It's {@code public} by design, so accessing and modifying it is as concise as possible.
     */
    public byte $;

    private ByteBox(byte value) {
        this.$ = value;
    }
}
