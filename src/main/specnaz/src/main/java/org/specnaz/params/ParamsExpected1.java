package org.specnaz.params;

import org.specnaz.TestSettings;
import org.specnaz.params.impl.ParametrizedPositiveTest1;

public final class ParamsExpected1<P> {
    private final ParametrizedPositiveTest1<P> parametrizedTest;
    private final TestSettings testSettings;

    public ParamsExpected1(ParametrizedPositiveTest1<P> parametrizedTest, TestSettings testSettings) {
        this.parametrizedTest = parametrizedTest;
        this.testSettings = testSettings;
    }

    @SafeVarargs
    public final TestSettings provided(P... params) {
        parametrizedTest.complete(params);
        return testSettings;
    }
}
