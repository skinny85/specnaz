package org.specnaz.utils;

public final class IntBox {
    public static IntBox boxWith(int value) {
        return new IntBox(value);
    }

    public int _;

    private IntBox(int value) {
        this._ = value;
    }
}
