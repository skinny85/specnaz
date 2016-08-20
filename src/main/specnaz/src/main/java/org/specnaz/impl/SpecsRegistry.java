package org.specnaz.impl;

import org.specnaz.SpecBuilder;
import org.specnaz.Specnaz;

import java.util.IdentityHashMap;
import java.util.function.Consumer;

import static java.lang.String.format;

public final class SpecsRegistry {
    private static final IdentityHashMap<Specnaz, SpecDescriptor> REGISTRY =
            new IdentityHashMap<>();

    public static void register(Specnaz specInstance, String description, Consumer<SpecBuilder> specClosure) {
        if (REGISTRY.containsKey(specInstance))
            throw new IllegalStateException(format(
                    "Specnaz.describes() was called multiple times in class '%s'",
                    specInstance.getClass().getSimpleName()));
        REGISTRY.put(specInstance, new SpecDescriptor(description, specClosure));
    }

    static SpecDescriptor specFor(Specnaz specInstance) {
        SpecDescriptor ret = REGISTRY.get(specInstance);
        if (ret == null)
            throw new IllegalStateException(format(
                    "Specnaz.describes() was never called in class '%s'",
                    specInstance.getClass().getSimpleName()));
        return ret;
    }

    SpecsRegistry() {
        throw new UnsupportedOperationException("Cannot create instances of SpecsRegistry");
    }
}
