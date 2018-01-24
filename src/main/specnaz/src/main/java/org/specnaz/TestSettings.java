package org.specnaz;

import java.lang.reflect.Method;

public final class TestSettings {
    private Method method;

    public TestSettings usingMethod(Method method) {
        this.method = method;
        return this;
    }

    public final class Wrapper {
        public Method method() {
            return TestSettings.this.method;
        }

        public TestSettings unwrap() {
            return TestSettings.this;
        }
    }
}
