package org.specnaz.examples.custom_dsl.given_when_then.standalone;

import org.specnaz.examples.custom_dsl.given_when_then.GivenBuilder;

import java.util.IdentityHashMap;
import java.util.function.Consumer;

public class GivenWhenThenRegistry {
    private static final IdentityHashMap<SpecnazGivenWhenThenStandalone, GivenWhenThenCoreWrapper> STORE = new IdentityHashMap<>();

    public static void add(SpecnazGivenWhenThenStandalone specInstance, String description, Consumer<GivenBuilder> specClosure)
            throws IllegalArgumentException {
        GivenWhenThenCoreWrapper prev = STORE.putIfAbsent(specInstance,
                new GivenWhenThenCoreWrapper(description, specClosure));
        if (prev != null)
            throw new IllegalArgumentException("Instance '" + specInstance + "' already registered");
    }

    static GivenWhenThenCoreWrapper get(SpecnazGivenWhenThenStandalone specInstance) throws IllegalArgumentException {
        GivenWhenThenCoreWrapper ret = STORE.get(specInstance);
        if (ret == null)
            throw new IllegalArgumentException("Instance '" + specInstance + "' was never registered");
        return ret;
    }
}
