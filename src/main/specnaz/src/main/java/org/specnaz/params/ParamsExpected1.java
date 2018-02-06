package org.specnaz.params;

import org.specnaz.TestSettings;
import org.specnaz.params.impl.ParametrizedTest1;

public final class ParamsExpected1<P> {
    private final ParametrizedTest1<P> parametrizedTest;
    private final TestSettings testSettings;

    public ParamsExpected1(ParametrizedTest1<P> parametrizedTest, TestSettings testSettings) {
        this.parametrizedTest = parametrizedTest;
        this.testSettings = testSettings;
    }

    @SafeVarargs
    public final TestSettings provided(P... params) {
        parametrizedTest.complete(params);
        return testSettings;
    }
}
