package org.specnaz.params.impl;

import org.specnaz.TestSettings;
import org.specnaz.params.Params2;
import org.specnaz.params.Params3;
import org.specnaz.params.Params4;
import org.specnaz.params.Params5;
import org.specnaz.params.Params6;
import org.specnaz.params.Params7;
import org.specnaz.params.Params8;
import org.specnaz.params.Params9;
import org.specnaz.params.TestClosureParams1;
import org.specnaz.params.TestClosureParams2;
import org.specnaz.params.TestClosureParams3;
import org.specnaz.params.TestClosureParams4;
import org.specnaz.params.TestClosureParams5;
import org.specnaz.params.TestClosureParams6;
import org.specnaz.params.TestClosureParams7;
import org.specnaz.params.TestClosureParams8;
import org.specnaz.params.TestClosureParams9;
import org.specnaz.utils.TestClosure;
import org.specnaz.utils.ThrowableExpectations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
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

    public static <P1, P2, P3, P4> TestSettings complete4p(AbstractParametrizedPositiveTest parametrizedTest,
            Stream<Params4<P1, P2, P3, P4>> params) {
        complete4(parametrizedTest, params);
        return parametrizedTest.testSettings;
    }

    public static <T extends Throwable, P1, P2, P3, P4> ThrowableExpectations<T> complete4e(
            AbstractParametrizedExceptionTest<T> parametrizedTest, Stream<Params4<P1, P2, P3, P4>> params) {
        complete4(parametrizedTest, params);
        return parametrizedTest.throwableExpectations;
    }

    public static <P1, P2, P3, P4> void complete4d(AbstractParametrizedSubgroup parametrizedSubgroup,
            Stream<Params4<P1, P2, P3, P4>> params) {
        parametrizedSubgroup.complete(paramsToLists4(params));
    }

    private static <P1, P2, P3, P4> void complete4(AbstractParametrizedTest parametrizedTest,
            Stream<Params4<P1, P2, P3, P4>> params) {
        parametrizedTest.complete(paramsToLists4(params));
    }

    private static <P1, P2, P3, P4> List<List<?>> paramsToLists4(Stream<Params4<P1, P2, P3, P4>> params) {
        return params
                .map(p -> Arrays.asList(p._1, p._2, p._3, p._4))
                .collect(Collectors.toList());
    }

    public static <P1, P2, P3, P4, P5> TestSettings complete5p(AbstractParametrizedPositiveTest parametrizedTest,
            Stream<Params5<P1, P2, P3, P4, P5>> params) {
        complete5(parametrizedTest, params);
        return parametrizedTest.testSettings;
    }

    public static <T extends Throwable, P1, P2, P3, P4, P5> ThrowableExpectations<T> complete5e(
            AbstractParametrizedExceptionTest<T> parametrizedTest, Stream<Params5<P1, P2, P3, P4, P5>> params) {
        complete5(parametrizedTest, params);
        return parametrizedTest.throwableExpectations;
    }

    public static <P1, P2, P3, P4, P5> void complete5d(AbstractParametrizedSubgroup parametrizedSubgroup,
            Stream<Params5<P1, P2, P3, P4, P5>> params) {
        parametrizedSubgroup.complete(paramsToLists5(params));
    }

    private static <T extends Throwable, P1, P2, P3, P4, P5> void complete5(
            AbstractParametrizedTest parametrizedTest, Stream<Params5<P1, P2, P3, P4, P5>> params) {
        parametrizedTest.complete(paramsToLists5(params));
    }

    private static <P1, P2, P3, P4, P5> List<List<?>> paramsToLists5(Stream<Params5<P1, P2, P3, P4, P5>> params) {
        return params
                .map(p -> Arrays.asList(p._1, p._2, p._3, p._4, p._5))
                .collect(Collectors.toList());
    }

    public static <P1, P2, P3, P4, P5, P6> TestSettings complete6p(AbstractParametrizedPositiveTest parametrizedTest,
            Stream<Params6<P1, P2, P3, P4, P5, P6>> params) {
        complete6(parametrizedTest, params);
        return parametrizedTest.testSettings;
    }

    public static <T extends Throwable, P1, P2, P3, P4, P5, P6> ThrowableExpectations<T> complete6e(
            AbstractParametrizedExceptionTest<T> parametrizedTest, Stream<Params6<P1, P2, P3, P4, P5, P6>> params) {
        complete6(parametrizedTest, params);
        return parametrizedTest.throwableExpectations;
    }

    public static <P1, P2, P3, P4, P5, P6> void complete6d(AbstractParametrizedSubgroup parametrizedSubgroup,
            Stream<Params6<P1, P2, P3, P4, P5, P6>> params) {
        parametrizedSubgroup.complete(paramsToLists6(params));
    }

    private static <P1, P2, P3, P4, P5, P6> void complete6(AbstractParametrizedTest parametrizedTest,
            Stream<Params6<P1, P2, P3, P4, P5, P6>> params) {
        parametrizedTest.complete(paramsToLists6(params));
    }

    private static <P1, P2, P3, P4, P5, P6> List<List<?>> paramsToLists6(Stream<Params6<P1, P2, P3, P4, P5, P6>> params) {
        return params
                .map(p -> Arrays.asList(p._1, p._2, p._3, p._4, p._5, p._6))
                .collect(Collectors.toList());
    }

    public static <P1, P2, P3, P4, P5, P6, P7> TestSettings complete7p(
            AbstractParametrizedPositiveTest parametrizedTest, Stream<Params7<P1, P2, P3, P4, P5, P6, P7>> params) {
        complete7(parametrizedTest, params);
        return parametrizedTest.testSettings;
    }

    public static <T extends Throwable, P1, P2, P3, P4, P5, P6, P7> ThrowableExpectations<T> complete7e(
            AbstractParametrizedExceptionTest<T> parametrizedTest, Stream<Params7<P1, P2, P3, P4, P5, P6, P7>> params) {
        complete7(parametrizedTest, params);
        return parametrizedTest.throwableExpectations;
    }

    public static <P1, P2, P3, P4, P5, P6, P7> void complete7d(AbstractParametrizedSubgroup parametrizedSubgroup,
            Stream<Params7<P1, P2, P3, P4, P5, P6, P7>> params) {
        parametrizedSubgroup.complete(paramsToLists7(params));
    }

    private static <P1, P2, P3, P4, P5, P6, P7> void complete7(AbstractParametrizedTest parametrizedTest,
            Stream<Params7<P1, P2, P3, P4, P5, P6, P7>> params) {
        parametrizedTest.complete(paramsToLists7(params));
    }

    private static <P1, P2, P3, P4, P5, P6, P7> List<List<?>> paramsToLists7(Stream<Params7<P1, P2, P3, P4, P5, P6, P7>> params) {
        return params
                .map(p -> Arrays.asList(p._1, p._2, p._3, p._4, p._5, p._6, p._7))
                .collect(Collectors.toList());
    }

    public static <P1, P2, P3, P4, P5, P6, P7, P8> TestSettings complete8p(AbstractParametrizedPositiveTest parametrizedTest,
            Stream<Params8<P1, P2, P3, P4, P5, P6, P7, P8>> params) {
        complete8(parametrizedTest, params);
        return parametrizedTest.testSettings;
    }

    public static <T extends Throwable, P1, P2, P3, P4, P5, P6, P7, P8> ThrowableExpectations<T> complete8e(
            AbstractParametrizedExceptionTest<T> parametrizedTest, Stream<Params8<P1, P2, P3, P4, P5, P6, P7, P8>> params) {
        complete8(parametrizedTest, params);
        return parametrizedTest.throwableExpectations;
    }

    public static <P1, P2, P3, P4, P5, P6, P7, P8> void complete8d(AbstractParametrizedSubgroup parametrizedSubgroup,
            Stream<Params8<P1, P2, P3, P4, P5, P6, P7, P8>> params) {
        parametrizedSubgroup.complete(paramsToLists8(params));
    }

    private static <P1, P2, P3, P4, P5, P6, P7, P8> void complete8(AbstractParametrizedTest parametrizedTest,
            Stream<Params8<P1, P2, P3, P4, P5, P6, P7, P8>> params) {
        parametrizedTest.complete(paramsToLists8(params));
    }

    private static <P1, P2, P3, P4, P5, P6, P7, P8> List<List<?>> paramsToLists8(
            Stream<Params8<P1, P2, P3, P4, P5, P6, P7, P8>> params) {
        return params
                .map(p -> Arrays.asList(p._1, p._2, p._3, p._4, p._5, p._6, p._7, p._8))
                .collect(Collectors.toList());
    }

    public static <P1, P2, P3, P4, P5, P6, P7, P8, P9> TestSettings complete9p(
            AbstractParametrizedPositiveTest parametrizedTest, Stream<Params9<P1, P2, P3, P4, P5, P6, P7, P8, P9>> params) {
        complete9(parametrizedTest, params);
        return parametrizedTest.testSettings;
    }

    public static <T extends Throwable, P1, P2, P3, P4, P5, P6, P7, P8, P9> ThrowableExpectations<T> complete9e(
            AbstractParametrizedExceptionTest<T> parametrizedTest, Stream<Params9<P1, P2, P3, P4, P5, P6, P7, P8, P9>> params) {
        complete9(parametrizedTest, params);
        return parametrizedTest.throwableExpectations;
    }

    public static <P1, P2, P3, P4, P5, P6, P7, P8, P9> void complete9d(AbstractParametrizedSubgroup parametrizedSubgroup,
            Stream<Params9<P1, P2, P3, P4, P5, P6, P7, P8, P9>> params) {
        parametrizedSubgroup.complete(paramsToLists9(params));
    }

    private static <P1, P2, P3, P4, P5, P6, P7, P8, P9> void complete9(AbstractParametrizedTest parametrizedTest,
            Stream<Params9<P1, P2, P3, P4, P5, P6, P7, P8, P9>> params) {
        parametrizedTest.complete(paramsToLists9(params));
    }

    private static <P1, P2, P3, P4, P5, P6, P7, P8, P9> List<List<?>> paramsToLists9(
            Stream<Params9<P1, P2, P3, P4, P5, P6, P7, P8, P9>> params) {
        return params
                .map(p -> Arrays.asList(p._1, p._2, p._3, p._4, p._5, p._6, p._7, p._8, p._9))
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

    public static <P1, P2, P3, P4> TestClosure toTestClosure4(TestClosureParams4<P1, P2, P3, P4> testBody, List<?> params) {
        return () -> testBody.invoke((P1) params.get(0), (P2) params.get(1), (P3) params.get(2), (P4) params.get(3));
    }

    public static <P1, P2, P3, P4, P5> TestClosure toTestClosure5(TestClosureParams5<P1, P2, P3, P4, P5> testBody, List<?> params) {
        return () -> testBody.invoke((P1) params.get(0), (P2) params.get(1), (P3) params.get(2), (P4) params.get(3),
                (P5) params.get(4));
    }

    public static <P1, P2, P3, P4, P5, P6> TestClosure toTestClosure6(TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody,
            List<?> params) {
        return () -> testBody.invoke((P1) params.get(0), (P2) params.get(1), (P3) params.get(2), (P4) params.get(3),
                (P5) params.get(4), (P6) params.get(5));
    }

    public static <P1, P2, P3, P4, P5, P6, P7> TestClosure toTestClosure7(
            TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody, List<?> params) {
        return () -> testBody.invoke((P1) params.get(0), (P2) params.get(1), (P3) params.get(2), (P4) params.get(3),
                (P5) params.get(4), (P6) params.get(5), (P7) params.get(6));
    }

    public static <P1, P2, P3, P4, P5, P6, P7, P8> TestClosure toTestClosure8(
            TestClosureParams8<P1, P2, P3, P4, P5, P6, P7, P8> testBody, List<?> params) {
        return () -> testBody.invoke((P1) params.get(0), (P2) params.get(1), (P3) params.get(2), (P4) params.get(3),
                (P5) params.get(4), (P6) params.get(5), (P7) params.get(6), (P8) params.get(7));
    }

    public static <P1, P2, P3, P4, P5, P6, P7, P8, P9> TestClosure toTestClosure9(
            TestClosureParams9<P1, P2, P3, P4, P5, P6, P7, P8, P9> testBody, List<?> params) {
        return () -> testBody.invoke((P1) params.get(0), (P2) params.get(1), (P3) params.get(2), (P4) params.get(3),
                (P5) params.get(4), (P6) params.get(5), (P7) params.get(6), (P8) params.get(7), (P9) params.get(8));
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
            ret = ret.replaceAll("%" + i,
                    Matcher.quoteReplacement(String.valueOf(params.get(i - 1))));
        }

        return ret;
    }
}
