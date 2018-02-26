package org.specnaz.params.impl;

import org.specnaz.core.CoreDslBuilder;
import org.specnaz.impl.SpecBuilderCoreDslAdapter;
import org.specnaz.params.ParamsExpected1;
import org.specnaz.params.ParamsExpected2;
import org.specnaz.params.ParamsExpected3;
import org.specnaz.params.ParamsExpected4;
import org.specnaz.params.ParamsExpected5;
import org.specnaz.params.ParamsExpected6;
import org.specnaz.params.ParamsExpected7;
import org.specnaz.params.ParamsExpected8;
import org.specnaz.params.ParamsExpectedException1;
import org.specnaz.params.ParamsExpectedException2;
import org.specnaz.params.ParamsExpectedException3;
import org.specnaz.params.ParamsExpectedException4;
import org.specnaz.params.ParamsExpectedException5;
import org.specnaz.params.ParamsExpectedException6;
import org.specnaz.params.ParamsExpectedException7;
import org.specnaz.params.ParamsExpectedSubgroup1;
import org.specnaz.params.ParamsExpectedSubgroup2;
import org.specnaz.params.ParamsExpectedSubgroup3;
import org.specnaz.params.ParamsExpectedSubgroup4;
import org.specnaz.params.ParamsExpectedSubgroup5;
import org.specnaz.params.ParamsExpectedSubgroup6;
import org.specnaz.params.ParamsSpecBuilder;
import org.specnaz.params.RunnableParams1;
import org.specnaz.params.RunnableParams2;
import org.specnaz.params.RunnableParams3;
import org.specnaz.params.RunnableParams4;
import org.specnaz.params.RunnableParams5;
import org.specnaz.params.RunnableParams6;
import org.specnaz.params.TestClosureParams1;
import org.specnaz.params.TestClosureParams2;
import org.specnaz.params.TestClosureParams3;
import org.specnaz.params.TestClosureParams4;
import org.specnaz.params.TestClosureParams5;
import org.specnaz.params.TestClosureParams6;
import org.specnaz.params.TestClosureParams7;
import org.specnaz.params.TestClosureParams8;

public final class ParamsSpecBuilderCoreDslAdapter extends SpecBuilderCoreDslAdapter
        implements ParamsSpecBuilder {
    public ParamsSpecBuilderCoreDslAdapter(CoreDslBuilder coreDslBuilder) {
        super(coreDslBuilder);
    }

    @Override
    public <P> ParamsExpected1<P> should(String description, TestClosureParams1<P> testBody) {
        return coreDslBuilder.parametrizedTest1(shouldDescription(description), testBody);
    }

    @Override
    public <P> ParamsExpected1<P> fshould(String description, TestClosureParams1<P> testBody) {
        return coreDslBuilder.focusedParametrizedTest1(shouldDescription(description), testBody);
    }

    @Override
    public <P> ParamsExpected1<P> xshould(String description, TestClosureParams1<P> testBody) {
        return coreDslBuilder.ignoredParametrizedTest1(shouldDescription(description), testBody);
    }

    @Override
    public <T extends Throwable, P> ParamsExpectedException1<T, P> shouldThrow(Class<T> expectedException,
            String description, TestClosureParams1<P> testBody) {
        return coreDslBuilder.parametrizedTestExpectingException1(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <T extends Throwable, P> ParamsExpectedException1<T, P> fshouldThrow(Class<T> expectedException,
            String description, TestClosureParams1<P> testBody) {
        return coreDslBuilder.focusedParametrizedTestExpectingException1(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <T extends Throwable, P> ParamsExpectedException1<T, P> xshouldThrow(Class<T> expectedException,
            String description, TestClosureParams1<P> testBody) {
        return coreDslBuilder.ignoredParametrizedTestExpectingException1(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <P1, P2> ParamsExpected2<P1, P2> should(String description, TestClosureParams2<P1, P2> testBody) {
        return coreDslBuilder.parametrizedTest2(shouldDescription(description), testBody);
    }

    @Override
    public <P1, P2> ParamsExpected2<P1, P2> fshould(String description, TestClosureParams2<P1, P2> testBody) {
        return coreDslBuilder.focusedParametrizedTest2(shouldDescription(description), testBody);
    }

    @Override
    public <P1, P2> ParamsExpected2<P1, P2> xshould(String description, TestClosureParams2<P1, P2> testBody) {
        return coreDslBuilder.ignoredParametrizedTest2(shouldDescription(description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> shouldThrow(Class<T> expectedException,
            String description, TestClosureParams2<P1, P2> testBody) {
        return coreDslBuilder.parametrizedTestExpectingException2(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> fshouldThrow(Class<T> expectedException,
            String description, TestClosureParams2<P1, P2> testBody) {
        return coreDslBuilder.focusedParametrizedTestExpectingException2(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> xshouldThrow(Class<T> expectedException,
            String description, TestClosureParams2<P1, P2> testBody) {
        return coreDslBuilder.ignoredParametrizedTestExpectingException2(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <P1, P2, P3> ParamsExpected3<P1, P2, P3> should(String description, TestClosureParams3<P1, P2, P3> testBody) {
        return coreDslBuilder.parametrizedTest3(shouldDescription(description), testBody);
    }

    @Override
    public <P1, P2, P3> ParamsExpected3<P1, P2, P3> fshould(String description, TestClosureParams3<P1, P2, P3> testBody) {
        return coreDslBuilder.focusedParametrizedTest3(shouldDescription(description), testBody);
    }

    @Override
    public <P1, P2, P3> ParamsExpected3<P1, P2, P3> xshould(String description, TestClosureParams3<P1, P2, P3> testBody) {
        return coreDslBuilder.ignoredParametrizedTest3(shouldDescription(description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2, P3> ParamsExpectedException3<T, P1, P2, P3> shouldThrow(
            Class<T> expectedException, String description, TestClosureParams3<P1, P2, P3> testBody) {
        return coreDslBuilder.parametrizedTestExpectingException3(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2, P3> ParamsExpectedException3<T, P1, P2, P3> fshouldThrow(
            Class<T> expectedException, String description, TestClosureParams3<P1, P2, P3> testBody) {
        return coreDslBuilder.focusedParametrizedTestExpectingException3(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2, P3> ParamsExpectedException3<T, P1, P2, P3> xshouldThrow(
            Class<T> expectedException, String description, TestClosureParams3<P1, P2, P3> testBody) {
        return coreDslBuilder.ignoredParametrizedTestExpectingException3(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <P1, P2, P3, P4> ParamsExpected4<P1, P2, P3, P4> should(String description,
            TestClosureParams4<P1, P2, P3, P4> testBody) {
        return coreDslBuilder.parametrizedTest4(shouldDescription(description), testBody);
    }

    @Override
    public <P1, P2, P3, P4> ParamsExpected4<P1, P2, P3, P4> fshould(String description,
            TestClosureParams4<P1, P2, P3, P4> testBody) {
        return coreDslBuilder.focusedParametrizedTest4(shouldDescription(description), testBody);
    }

    @Override
    public <P1, P2, P3, P4> ParamsExpected4<P1, P2, P3, P4> xshould(String description,
            TestClosureParams4<P1, P2, P3, P4> testBody) {
        return coreDslBuilder.ignoredParametrizedTest4(shouldDescription(description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2, P3, P4> ParamsExpectedException4<T, P1, P2, P3, P4> shouldThrow(
            Class<T> expectedException, String description, TestClosureParams4<P1, P2, P3, P4> testBody) {
        return coreDslBuilder.parametrizedTestExpectingException4(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2, P3, P4> ParamsExpectedException4<T, P1, P2, P3, P4> fshouldThrow(
            Class<T> expectedException, String description, TestClosureParams4<P1, P2, P3, P4> testBody) {
        return coreDslBuilder.focusedParametrizedTestExpectingException4(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2, P3, P4> ParamsExpectedException4<T, P1, P2, P3, P4> xshouldThrow(
            Class<T> expectedException, String description, TestClosureParams4<P1, P2, P3, P4> testBody) {
        return coreDslBuilder.ignoredParametrizedTestExpectingException4(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <P1, P2, P3, P4, P5> ParamsExpected5<P1, P2, P3, P4, P5> should(
            String description, TestClosureParams5<P1, P2, P3, P4, P5> testBody) {
        return coreDslBuilder.parametrizedTest5(shouldDescription(description), testBody);
    }

    @Override
    public <P1, P2, P3, P4, P5> ParamsExpected5<P1, P2, P3, P4, P5> fshould(
            String description, TestClosureParams5<P1, P2, P3, P4, P5> testBody) {
        return coreDslBuilder.focusedParametrizedTest5(shouldDescription(description), testBody);
    }

    @Override
    public <P1, P2, P3, P4, P5> ParamsExpected5<P1, P2, P3, P4, P5> xshould(
            String description, TestClosureParams5<P1, P2, P3, P4, P5> testBody) {
        return coreDslBuilder.ignoredParametrizedTest5(shouldDescription(description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2, P3, P4, P5> ParamsExpectedException5<T, P1, P2, P3, P4, P5> shouldThrow(
            Class<T> expectedException, String description, TestClosureParams5<P1, P2, P3, P4, P5> testBody) {
        return coreDslBuilder.parametrizedTestExpectingException5(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2, P3, P4, P5> ParamsExpectedException5<T, P1, P2, P3, P4, P5> fshouldThrow(
            Class<T> expectedException, String description, TestClosureParams5<P1, P2, P3, P4, P5> testBody) {
        return coreDslBuilder.focusedParametrizedTestExpectingException5(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2, P3, P4, P5> ParamsExpectedException5<T, P1, P2, P3, P4, P5> xshouldThrow(
            Class<T> expectedException, String description, TestClosureParams5<P1, P2, P3, P4, P5> testBody) {
        return coreDslBuilder.ignoredParametrizedTestExpectingException5(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <P1, P2, P3, P4, P5, P6> ParamsExpected6<P1, P2, P3, P4, P5, P6> should(String description,
            TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody) {
        return coreDslBuilder.parametrizedTest6(shouldDescription(description), testBody);
    }

    @Override
    public <P1, P2, P3, P4, P5, P6> ParamsExpected6<P1, P2, P3, P4, P5, P6> fshould(String description,
            TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody) {
        return coreDslBuilder.focusedParametrizedTest6(shouldDescription(description), testBody);
    }

    @Override
    public <P1, P2, P3, P4, P5, P6> ParamsExpected6<P1, P2, P3, P4, P5, P6> xshould(String description,
            TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody) {
        return coreDslBuilder.ignoredParametrizedTest6(shouldDescription(description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2, P3, P4, P5, P6> ParamsExpectedException6<T, P1, P2, P3, P4, P5, P6> shouldThrow(
            Class<T> expectedException, String description, TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody) {
        return coreDslBuilder.parametrizedTestExpectingException6(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2, P3, P4, P5, P6> ParamsExpectedException6<T, P1, P2, P3, P4, P5, P6> fshouldThrow(
            Class<T> expectedException, String description, TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody) {
        return coreDslBuilder.focusedParametrizedTestExpectingException6(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2, P3, P4, P5, P6> ParamsExpectedException6<T, P1, P2, P3, P4, P5, P6> xshouldThrow(
            Class<T> expectedException, String description, TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody) {
        return coreDslBuilder.ignoredParametrizedTestExpectingException6(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <P1, P2, P3, P4, P5, P6, P7> ParamsExpected7<P1, P2, P3, P4, P5, P6, P7> should(
            String description, TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody) {
        return coreDslBuilder.parametrizedTest7(shouldDescription(description), testBody);
    }

    @Override
    public <P1, P2, P3, P4, P5, P6, P7> ParamsExpected7<P1, P2, P3, P4, P5, P6, P7> fshould(
            String description, TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody) {
        return coreDslBuilder.focusedParametrizedTest7(shouldDescription(description), testBody);
    }

    @Override
    public <P1, P2, P3, P4, P5, P6, P7> ParamsExpected7<P1, P2, P3, P4, P5, P6, P7> xshould(
            String description, TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody) {
        return coreDslBuilder.ignoredParametrizedTest7(shouldDescription(description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2, P3, P4, P5, P6, P7> ParamsExpectedException7<T, P1, P2, P3, P4, P5, P6, P7> shouldThrow(
            Class<T> expectedException, String description, TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody) {
        return coreDslBuilder.parametrizedTestExpectingException7(
                expectedException, shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2, P3, P4, P5, P6, P7> ParamsExpectedException7<T, P1, P2, P3, P4, P5, P6, P7> fshouldThrow(
            Class<T> expectedException, String description, TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody) {
        return coreDslBuilder.focusedParametrizedTestExpectingException7(
                expectedException, shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2, P3, P4, P5, P6, P7> ParamsExpectedException7<T, P1, P2, P3, P4, P5, P6, P7> xshouldThrow(
            Class<T> expectedException, String description, TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody) {
        return coreDslBuilder.ignoredParametrizedTestExpectingException7(
                expectedException, shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <P1, P2, P3, P4, P5, P6, P7, P8> ParamsExpected8<P1, P2, P3, P4, P5, P6, P7, P8> should(String description, TestClosureParams8<P1, P2, P3, P4, P5, P6, P7, P8> testBody) {
        return coreDslBuilder.parametrizedTest8(shouldDescription(description), testBody);
    }

    @Override
    public <P1, P2, P3, P4, P5, P6, P7, P8> ParamsExpected8<P1, P2, P3, P4, P5, P6, P7, P8> fshould(String description, TestClosureParams8<P1, P2, P3, P4, P5, P6, P7, P8> testBody) {
        return coreDslBuilder.focusedParametrizedTest8(shouldDescription(description), testBody);
    }

    @Override
    public <P1, P2, P3, P4, P5, P6, P7, P8> ParamsExpected8<P1, P2, P3, P4, P5, P6, P7, P8> xshould(String description, TestClosureParams8<P1, P2, P3, P4, P5, P6, P7, P8> testBody) {
        return coreDslBuilder.ignoredParametrizedTest8(shouldDescription(description), testBody);
    }

    @Override
    public <P> ParamsExpectedSubgroup1<P> describes(String description, RunnableParams1<P> specClosure) {
        return coreDslBuilder.parametrizedSubSpecification1(description, specClosure);
    }

    @Override
    public <P> ParamsExpectedSubgroup1<P> fdescribes(String description, RunnableParams1<P> specClosure) {
        return coreDslBuilder.focusedParametrizedSubSpecification1(description, specClosure);
    }

    @Override
    public <P> ParamsExpectedSubgroup1<P> xdescribes(String description, RunnableParams1<P> specClosure) {
        return coreDslBuilder.ignoredParametrizedSubSpecification1(description, specClosure);
    }

    @Override
    public <P1, P2> ParamsExpectedSubgroup2<P1, P2> describes(String description,
            RunnableParams2<P1, P2> specClosure) {
        return coreDslBuilder.parametrizedSubSpecification2(description, specClosure);
    }

    @Override
    public <P1, P2> ParamsExpectedSubgroup2<P1, P2> fdescribes(String description,
            RunnableParams2<P1, P2> specClosure) {
        return coreDslBuilder.focusedParametrizedSubSpecification2(description, specClosure);
    }

    @Override
    public <P1, P2> ParamsExpectedSubgroup2<P1, P2> xdescribes(String description,
            RunnableParams2<P1, P2> specClosure) {
        return coreDslBuilder.ignoredParametrizedSubSpecification2(description, specClosure);
    }

    @Override
    public <P1, P2, P3> ParamsExpectedSubgroup3<P1, P2, P3> describes(String description,
            RunnableParams3<P1, P2, P3> specClosure) {
        return coreDslBuilder.parametrizedSubSpecification3(description, specClosure);
    }

    @Override
    public <P1, P2, P3> ParamsExpectedSubgroup3<P1, P2, P3> fdescribes(String description,
            RunnableParams3<P1, P2, P3> specClosure) {
        return coreDslBuilder.focusedParametrizedSubSpecification3(description, specClosure);
    }

    @Override
    public <P1, P2, P3> ParamsExpectedSubgroup3<P1, P2, P3> xdescribes(String description,
            RunnableParams3<P1, P2, P3> specClosure) {
        return coreDslBuilder.ignoredParametrizedSubSpecification3(description, specClosure);
    }

    @Override
    public <P1, P2, P3, P4> ParamsExpectedSubgroup4<P1, P2, P3, P4> describes(String description,
            RunnableParams4<P1, P2, P3, P4> specClosure) {
        return coreDslBuilder.parametrizedSubSpecification4(description, specClosure);
    }

    @Override
    public <P1, P2, P3, P4> ParamsExpectedSubgroup4<P1, P2, P3, P4> fdescribes(String description,
            RunnableParams4<P1, P2, P3, P4> specClosure) {
        return coreDslBuilder.focusedParametrizedSubSpecification4(description, specClosure);
    }

    @Override
    public <P1, P2, P3, P4> ParamsExpectedSubgroup4<P1, P2, P3, P4> xdescribes(String description,
            RunnableParams4<P1, P2, P3, P4> specClosure) {
        return coreDslBuilder.ignoredParametrizedSubSpecification4(description, specClosure);
    }

    @Override
    public <P1, P2, P3, P4, P5> ParamsExpectedSubgroup5<P1, P2, P3, P4, P5> describes(String description,
            RunnableParams5<P1, P2, P3, P4, P5> specClosure) {
        return coreDslBuilder.parametrizedSubSpecification5(description, specClosure);
    }

    @Override
    public <P1, P2, P3, P4, P5> ParamsExpectedSubgroup5<P1, P2, P3, P4, P5> fdescribes(String description,
            RunnableParams5<P1, P2, P3, P4, P5> specClosure) {
        return coreDslBuilder.focusedParametrizedSubSpecification5(description, specClosure);
    }

    @Override
    public <P1, P2, P3, P4, P5> ParamsExpectedSubgroup5<P1, P2, P3, P4, P5> xdescribes(String description,
            RunnableParams5<P1, P2, P3, P4, P5> specClosure) {
        return coreDslBuilder.ignoredParametrizedSubSpecification5(description, specClosure);
    }

    @Override
    public <P1, P2, P3, P4, P5, P6> ParamsExpectedSubgroup6<P1, P2, P3, P4, P5, P6> describes(
            String description, RunnableParams6<P1, P2, P3, P4, P5, P6> specClosure) {
        return coreDslBuilder.parametrizedSubSpecification6(description, specClosure);
    }

    @Override
    public <P1, P2, P3, P4, P5, P6> ParamsExpectedSubgroup6<P1, P2, P3, P4, P5, P6> fdescribes(
            String description, RunnableParams6<P1, P2, P3, P4, P5, P6> specClosure) {
        return coreDslBuilder.focusedParametrizedSubSpecification6(description, specClosure);
    }

    @Override
    public <P1, P2, P3, P4, P5, P6> ParamsExpectedSubgroup6<P1, P2, P3, P4, P5, P6> xdescribes(
            String description, RunnableParams6<P1, P2, P3, P4, P5, P6> specClosure) {
        return coreDslBuilder.ignoredParametrizedSubSpecification6(description, specClosure);
    }
}
