package org.specnaz.impl;

public class SpecsRegistryViolation extends Exception {
    public SpecsRegistryViolation(String format, Object... args) {
        super(String.format(format, args));
    }
}
