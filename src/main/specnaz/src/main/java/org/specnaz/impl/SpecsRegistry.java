package org.specnaz.impl;

import org.specnaz.SpecBuilder;
import org.specnaz.Specnaz;

import java.util.IdentityHashMap;
import java.util.function.Consumer;

import static java.lang.String.format;

public final class SpecsRegistry {
    private static final IdentityHashMap<Object, SpecDescriptor> REGISTRY =
            new IdentityHashMap<>();

    public static void register(Object specInstance, String description,
                                Consumer<SpecBuilder> specClosure) throws IllegalStateException {
        SpecDescriptor prev = REGISTRY.putIfAbsent(specInstance,
                new SpecDescriptor(description, specClosure));
        if (prev != null)
            throw new IllegalStateException(format(
                    "Test object '%s' already registered", specInstance));
    }

    static SpecDescriptor specFor(Object specInstance) throws IllegalStateException {
        SpecDescriptor ret = REGISTRY.get(specInstance);
        if (ret == null)
            throw new IllegalStateException(format(
                    "Test object '%s' was never registered", specInstance));
        return ret;
    }

    SpecsRegistry() {
        throw new UnsupportedOperationException("Cannot create instances of SpecsRegistry");
    }
}
