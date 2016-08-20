package org.specnaz.utils;

import static java.util.Objects.requireNonNull;

public final class Box<T> {
    public static <T> Box<T> emptyBox() {
        return new Box<>(null);
    }

    public static <T> Box<T> boxWith(T thing) throws NullPointerException {
        return new Box<>(requireNonNull(thing));
    }

    public T $;

    private Box(T thing) {
        this.$ = thing;
    }
}
