package org.specnaz.junit.platform;

import org.specnaz.junit.platform.impl.IsSpecnazClassPredicate;

import java.util.function.Predicate;

public final class SpecnazJUnitPlatformTestEngine extends CommonSpecnazJUnitPlatformTestEngine {
    @Override
    public String getId() {
        return "specnaz";
    }

    @Override
    protected Predicate<Class<?>> classPredicate() {
        return IsSpecnazClassPredicate.INSTANCE;
    }
}
