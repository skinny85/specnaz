package org.specnaz.params;

import org.specnaz.TestSettings;
import org.specnaz.params.impl.Conversions;
import org.specnaz.params.impl.ParametrizedPositiveTest2;

public final class ParamsExpected2<P1, P2> {
    private final ParametrizedPositiveTest2<P1, P2> parametrizedTest;
    private final TestSettings testSettings;

    public ParamsExpected2(ParametrizedPositiveTest2<P1, P2> parametrizedTest, TestSettings testSettings) {
        this.parametrizedTest = parametrizedTest;
        this.testSettings = testSettings;
    }

    @SafeVarargs
    public final TestSettings provided(Params2<P1, P2>... params) {
        Conversions.complete2(parametrizedTest, params);
        return testSettings;
    }
}
