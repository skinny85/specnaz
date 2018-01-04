package org.specnaz.junit.impl;

import org.junit.runners.model.FrameworkMethod;

public class StubMethod {
    public static FrameworkMethod frameworkMethod() {
        try {
            return new FrameworkMethod(StubMethod.class.getMethod("method"));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public void method() {

    }
}
