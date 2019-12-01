package org.specnaz;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Allows you to tweak the settings of an individual test.
 * An instance of this class is returned from {@link SpecBuilder#should}.
 *
 * @see #usingMethod
 */
public final class TestSettings {
    private Method method;

    /**
     * Sets the {@link Method} used for this test.
     * This is mainly useful for external integrations -
     * for example, Spring's JUnit 4 Rules use annotations on the test
     * method for different behaviors.
     * <p>
     * There's a helper included with Specnaz that tries to make finding the parameter
     * to pass to this method easier, {@link org.specnaz.utils.Utils#findMethod}.
     *
     * @param method the {@link Method} to use
     * @return {@code this}
     * @see org.specnaz.utils.Utils#findMethod
     */
    public TestSettings usingMethod(Method method) {
        this.method = method;
        return this;
    }

    /**
     * This is a wrapper class used in the implementation of Specnaz.
     * You never need it when writing tests,
     * it's just an implementation detail.
     */
    public static final class Wrapper {
        private final TestSettings inner;

        public Wrapper(TestSettings inner) {
            this.inner = inner;
        }

        public Method method() {
            return inner.method;
        }

        public Annotation[] annotations() {
            Method method = method();
            return method == null
                    ? new Annotation[]{}
                    : method.getAnnotations();
        }
    }
}
