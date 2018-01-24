package org.specnaz.junit.impl;

import org.junit.runners.model.FrameworkMethod;
import org.specnaz.impl.ExecutableTestCase;

import java.lang.reflect.Method;

public final class JUnitFrameworkMethod {
    public static FrameworkMethod frameworkMethod(ExecutableTestCase executableTestCase) {
        Method method = executableTestCase.testCase.testSettings().method();
        return method == null ? placeholderFrameworkMethod() : frameworkMethod(method);
    }

    private static FrameworkMethod placeholderFrameworkMethod() {
        Method stubMethod;
        try {
            stubMethod = JUnitFrameworkMethod.class.getMethod("placeholderMethod");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return frameworkMethod(stubMethod);
    }

    private static FrameworkMethod frameworkMethod(Method method) {
        return new FrameworkMethod(method);
    }

    public void placeholderMethod() {
    }
}
