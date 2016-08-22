package org.specnaz.utils;

/**
 * A version of the {@link Box} class for primitive {@code long} values.
 * For code readability, its constructor is private;
 * you create instances using the {@link LongBox#boxWith(long value)} static method.
 *
 * @see Box
 */
public final class LongBox {
    /**
     * Creates a new {@link LongBox} wrapping the provided value.
     *
     * @param value
     *     the value to wrap in a box
     * @return
     *     a new instance of the {@link LongBox} class with the {@link LongBox#$} field set to {@code value}
     */
    public static LongBox boxWith(long value) {
        return new LongBox(value);
    }

    /**
     * The value wrapped in the box.
     * It's {@code public} by design, so accessing and modifying it is as concise as possible.
     */
    public long $;

    private LongBox(long value) {
        this.$ = value;
    }
}
