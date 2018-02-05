package org.specnaz.params;

import org.specnaz.TestSettings;
import org.specnaz.params.impl.ParametrizedTest1;

public final class ParamsExpected1<T> {
    private final ParametrizedTest1<T> parametrizedTest;
    private final TestSettings testSettings;

    public ParamsExpected1(ParametrizedTest1<T> parametrizedTest, TestSettings testSettings) {
        this.parametrizedTest = parametrizedTest;
        this.testSettings = testSettings;
    }

    @SafeVarargs
    public final TestSettings provided(T... params) {
        parametrizedTest.complete(params);
        return testSettings;
    }
}
