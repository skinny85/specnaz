package org.specnaz.junit.platform;

import org.specnaz.Specnaz;
import org.specnaz.params.SpecnazParams;

import java.lang.reflect.Modifier;
import java.util.function.Predicate;

public enum IsSpecnazClassPredicate implements Predicate<Class<?>> {
    INSTANCE;

    @Override
    public boolean test(Class<?> classs) {
        return (Specnaz.class.isAssignableFrom(classs) ||
                SpecnazParams.class.isAssignableFrom(classs)) && !Modifier.isAbstract(classs.getModifiers());
    }
}
