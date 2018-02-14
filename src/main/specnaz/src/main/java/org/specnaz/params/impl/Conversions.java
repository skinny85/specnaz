package org.specnaz.params.impl;

import org.specnaz.params.Params2;
import org.specnaz.params.TestClosureParams1;
import org.specnaz.params.TestClosureParams2;
import org.specnaz.utils.TestClosure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class Conversions {
    @SafeVarargs
    public static <P> void complete1(AbstractParametrizedTest parametrizedTest, P... params) {
        parametrizedTest.complete(Stream.of(params)
                .map(p -> Collections.singletonList(p))
                .collect(Collectors.toList()));
    }

    public static <P> void complete1(AbstractParametrizedTest parametrizedTest, Iterable<? extends P> params) {
        parametrizedTest.complete(StreamSupport.stream(params.spliterator(), false)
                .map(p -> Collections.singletonList(p))
                .collect(Collectors.toList()));
    }

    @SafeVarargs
    public static <P1, P2> void complete2(AbstractParametrizedTest parametrizedTest, Params2<P1, P2>... params) {
        parametrizedTest.complete(Stream.of(params)
                .map(p -> Arrays.asList(p._1, p._2))
                .collect(Collectors.toList()));
    }

    public static <P1, P2> void complete2(AbstractParametrizedTest parametrizedTest, Iterable<Params2<P1, P2>> params) {
        parametrizedTest.complete(StreamSupport.stream(params.spliterator(), false)
                .map(p -> Arrays.asList(p._1, p._2))
                .collect(Collectors.toList()));
    }

    public static <P> TestClosure toTestClosure1(TestClosureParams1<P> testBody, List<?> params) {
        return () -> testBody.invoke((P) params.get(0));
    }

    public static <P1, P2> TestClosure toTestClosure2(TestClosureParams2<P1, P2> testBody, List<?> params) {
        return () -> testBody.invoke((P1) params.get(0), (P2) params.get(1));
    }
}
