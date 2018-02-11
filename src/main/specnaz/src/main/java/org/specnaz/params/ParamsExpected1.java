package org.specnaz.params;

import org.specnaz.TestSettings;
import org.specnaz.params.impl.ParametrizedPositiveTest1;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ParamsExpected1<P> {
    private final ParametrizedPositiveTest1<P> parametrizedTest;
    private final TestSettings testSettings;

    public ParamsExpected1(ParametrizedPositiveTest1<P> parametrizedTest, TestSettings testSettings) {
        this.parametrizedTest = parametrizedTest;
        this.testSettings = testSettings;
    }

    @SafeVarargs
    public final TestSettings provided(P... params) {
        parametrizedTest.complete(Stream.of(params).map(p -> Collections.singletonList(p)).collect(Collectors.toList()));
        return testSettings;
    }
}
