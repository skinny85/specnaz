package org.specnaz.params;

import org.specnaz.TestSettings;
import org.specnaz.params.impl.ParametrizedPositiveTest2;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ParamsExpected2<P1, P2> {
    private final ParametrizedPositiveTest2<P1, P2> parametrizedTest;
    private final TestSettings testSettings;

    public ParamsExpected2(ParametrizedPositiveTest2<P1, P2> parametrizedTest, TestSettings testSettings) {
        this.parametrizedTest = parametrizedTest;
        this.testSettings = testSettings;
    }

    @SafeVarargs
    public final TestSettings provided(Params2<P1, P2>... params) {
        parametrizedTest.complete(Stream.of(params).map(p -> Arrays.asList(p._1, p._2)).collect(Collectors.toList()));
        return testSettings;
    }
}
