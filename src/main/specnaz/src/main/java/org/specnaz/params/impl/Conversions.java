package org.specnaz.params.impl;

import org.specnaz.TestSettings;
import org.specnaz.params.Params2;
import org.specnaz.params.Params3;
import org.specnaz.params.TestClosureParams1;
import org.specnaz.params.TestClosureParams2;
import org.specnaz.params.TestClosureParams3;
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

    public static <P> void complete1d(AbstractParametrizedSubgroup parametrizedSubgroup, Stream<? extends P> params) {
        parametrizedSubgroup.complete(paramsToLists1(params));
    }

    private static <P> void complete1(AbstractParametrizedTest parametrizedTest, Stream<? extends P> params) {
        parametrizedTest.complete(paramsToLists1(params));
    }

    private static <P> List<List<?>> paramsToLists1(Stream<? extends P> params) {
        return params
                .map(p -> Collections.singletonList(p))
                .collect(Collectors.toList());
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

    public static <P1, P2> void complete2d(AbstractParametrizedSubgroup parametrizedSubgroup,
            Stream<Params2<P1, P2>> params) {
        parametrizedSubgroup.complete(paramsToLists2(params));
    }

    private static <P1, P2> void complete2(
            AbstractParametrizedTest parametrizedTest, Stream<Params2<P1, P2>> params) {
        parametrizedTest.complete(paramsToLists2(params));
    }

    private static <P1, P2> List<List<?>> paramsToLists2(Stream<Params2<P1, P2>> params) {
        return params
                .map(p -> Arrays.asList(p._1, p._2))
                .collect(Collectors.toList());
    }

    public static <P1, P2, P3> TestSettings complete3p(AbstractParametrizedPositiveTest parametrizedTest,
            Stream<Params3<P1, P2, P3>> params) {
        complete3(parametrizedTest, params);
        return parametrizedTest.testSettings;
    }

    public static <T extends Throwable, P1, P2, P3> ThrowableExpectations<T> complete3e(
            AbstractParametrizedExceptionTest<T> parametrizedTest, Stream<Params3<P1, P2, P3>> params) {
        complete3(parametrizedTest, params);
        return parametrizedTest.throwableExpectations;
    }

    public static <P1, P2, P3> void complete3d(AbstractParametrizedSubgroup parametrizedSubgroup,
            Stream<Params3<P1, P2, P3>> params) {
        parametrizedSubgroup.complete(paramsToLists3(params));
    }

    private static <P1, P2, P3> void complete3(
            AbstractParametrizedTest parametrizedTest, Stream<Params3<P1, P2, P3>> params) {
        parametrizedTest.complete(paramsToLists3(params));
    }

    private static <P1, P2, P3> List<List<?>> paramsToLists3(Stream<Params3<P1, P2, P3>> params) {
        return params
                .map(p -> Arrays.asList(p._1, p._2, p._3))
                .collect(Collectors.toList());
    }

    public static <P> TestClosure toTestClosure1(TestClosureParams1<P> testBody, List<?> params) {
        return () -> testBody.invoke((P) params.get(0));
    }

    public static <P1, P2> TestClosure toTestClosure2(TestClosureParams2<P1, P2> testBody, List<?> params) {
        return () -> testBody.invoke((P1) params.get(0), (P2) params.get(1));
    }

    public static <P1, P2, P3> TestClosure toTestClosure3(TestClosureParams3<P1, P2, P3> testBody, List<?> params) {
        return () -> testBody.invoke((P1) params.get(0), (P2) params.get(1), (P3) params.get(2));
    }

    public static String formatParamsDesc(String description, List<?> params) {
        if (!description.contains("%"))
            return description;

        String ret = description;
        int size = params.size();
        // we do it in reverse, so that '%10' gets processed
        // before '%1' - not that we support '%10' now anyway,
        // but it's probably better to be aware of this issue
        // sooner rather than later :)
        for (int i = size; i > 0; i--) {
            ret = ret.replaceAll("%" + i, String.valueOf(params.get(i - 1)));
        }

        return ret;
    }
}
