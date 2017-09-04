package org.specnaz.impl;

import org.specnaz.core.CoreDslBuilder;

import java.util.IdentityHashMap;
import java.util.function.Consumer;

import static java.lang.String.format;

public final class SpecsRegistry {
    private static final IdentityHashMap<Object, SpecDescriptor> REGISTRY =
            new IdentityHashMap<>();

    public static void register(Object specInstance, String description,
                                TestCaseType testCaseType,
                                Consumer<CoreDslBuilder> specClosure) throws SpecsRegistryViolation {
        SpecDescriptor prev = REGISTRY.putIfAbsent(specInstance,
                new SpecDescriptor(description, testCaseType, specClosure));
        if (prev != null)
            throw new SpecsRegistryViolation(
                    "Test object '%s' already registered", specInstance);
    }

    static SpecDescriptor specFor(Object specInstance) throws SpecsRegistryViolation {
        SpecDescriptor ret = REGISTRY.get(specInstance);
        if (ret == null)
            throw new SpecsRegistryViolation(
                    "Test object '%s' was never registered", specInstance);
        return ret;
    }

    SpecsRegistry() {
        throw new UnsupportedOperationException("Cannot create instances of SpecsRegistry");
    }
}
