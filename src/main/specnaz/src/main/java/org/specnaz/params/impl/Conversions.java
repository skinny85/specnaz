package org.specnaz.params.impl;

import org.specnaz.TestSettings;
import org.specnaz.params.Params2;
import org.specnaz.params.TestClosureParams1;
import org.specnaz.params.TestClosureParams2;
import org.specnaz.utils.TestClosure;
import org.specnaz.utils.ThrowableExpectations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class Conversions {
    public static <E> Stream<E> iterable2stream(Iterable<E> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public static <P> TestSettings complete1p(
            AbstractParametrizedPositiveTest parametrizedTest, Stream<? extends P> params) {
        complete1(parametrizedTest, params);
        return parametrizedTest.testSettings;
    }

    public static <T extends Throwable, P> ThrowableExpectations<T> complete1e(
            AbstractParametrizedExceptionTest<T> parametrizedTest, Stream<? extends P> params) {
        complete1(parametrizedTest, params);
        return parametrizedTest.throwableExpectations;
    }

    private static <P> void complete1(AbstractParametrizedTest parametrizedTest, Stream<? extends P> params) {
        parametrizedTest.complete(params
                .map(p -> Collections.singletonList(p))
                .collect(Collectors.toList()));
    }

    public static <P1, P2> TestSettings complete2p(
            AbstractParametrizedPositiveTest parametrizedTest, Stream<Params2<P1, P2>> params) {
        complete2(parametrizedTest, params);
        return parametrizedTest.testSettings;
    }

    public static <T extends Throwable, P1, P2> ThrowableExpectations<T> complete2e(
            AbstractParametrizedExceptionTest<T> parametrizedTest, Stream<Params2<P1, P2>> params) {
        complete2(parametrizedTest, params);
        return parametrizedTest.throwableExpectations;
    }

    private static <P1, P2> void complete2(
            AbstractParametrizedTest parametrizedTest, Stream<Params2<P1, P2>> params) {
        parametrizedTest.complete(params
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
