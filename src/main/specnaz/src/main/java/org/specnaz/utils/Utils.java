package org.specnaz.utils;

import java.lang.reflect.Method;

public final class Utils {
    public static Method findMethod(Object spec, String methodName, Class<?>... parameterTypes) {
        try {
            return spec.getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
