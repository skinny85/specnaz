package org.specnaz.params.impl;

import org.specnaz.core.CoreDslBuilder;
import org.specnaz.impl.SpecBuilderCoreDslAdapter;
import org.specnaz.params.ParamsExpected1;
import org.specnaz.params.ParamsExpected2;
import org.specnaz.params.ParamsExpected3;
import org.specnaz.params.ParamsExpectedException1;
import org.specnaz.params.ParamsExpectedException2;
import org.specnaz.params.ParamsExpectedException3;
import org.specnaz.params.ParamsSpecBuilder;
import org.specnaz.params.TestClosureParams1;
import org.specnaz.params.TestClosureParams2;
import org.specnaz.params.TestClosureParams3;

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
    public <T extends Throwable, P> ParamsExpectedException1<T, P> shouldThrow(Class<T> expectedException,
            String description, TestClosureParams1<P> testBody) {
        return coreDslBuilder.parametrizedTestExpectingException1(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <P> ParamsExpected1<P> fshould(String description, TestClosureParams1<P> testBody) {
        return coreDslBuilder.focusedParametrizedTest1(shouldDescription(description), testBody);
    }

    @Override
    public <T extends Throwable, P> ParamsExpectedException1<T, P> fshouldThrow(Class<T> expectedException,
            String description, TestClosureParams1<P> testBody) {
        return coreDslBuilder.focusedParametrizedTestExpectingException1(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <P> ParamsExpected1<P> xshould(String description, TestClosureParams1<P> testBody) {
        return coreDslBuilder.ignoredParametrizedTest1(shouldDescription(description), testBody);
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
    public <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> shouldThrow(Class<T> expectedException,
            String description, TestClosureParams2<P1, P2> testBody) {
        return coreDslBuilder.parametrizedTestExpectingException2(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <P1, P2> ParamsExpected2<P1, P2> fshould(String description, TestClosureParams2<P1, P2> testBody) {
        return coreDslBuilder.focusedParametrizedTest2(shouldDescription(description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> fshouldThrow(Class<T> expectedException,
            String description, TestClosureParams2<P1, P2> testBody) {
        return coreDslBuilder.focusedParametrizedTestExpectingException2(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <P1, P2> ParamsExpected2<P1, P2> xshould(String description, TestClosureParams2<P1, P2> testBody) {
        return coreDslBuilder.ignoredParametrizedTest2(shouldDescription(description), testBody);
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
    public <T extends Throwable, P1, P2, P3> ParamsExpectedException3<T, P1, P2, P3> shouldThrow(
            Class<T> expectedException, String description, TestClosureParams3<P1, P2, P3> testBody) {
        return coreDslBuilder.parametrizedTestExpectingException3(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <P1, P2, P3> ParamsExpected3<P1, P2, P3> fshould(String description, TestClosureParams3<P1, P2, P3> testBody) {
        return coreDslBuilder.focusedParametrizedTest3(shouldDescription(description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2, P3> ParamsExpectedException3<T, P1, P2, P3> fshouldThrow(
            Class<T> expectedException, String description, TestClosureParams3<P1, P2, P3> testBody) {
        return coreDslBuilder.focusedParametrizedTestExpectingException3(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }

    @Override
    public <P1, P2, P3> ParamsExpected3<P1, P2, P3> xshould(String description, TestClosureParams3<P1, P2, P3> testBody) {
        return coreDslBuilder.ignoredParametrizedTest3(shouldDescription(description), testBody);
    }

    @Override
    public <T extends Throwable, P1, P2, P3> ParamsExpectedException3<T, P1, P2, P3> xshouldThrow(
            Class<T> expectedException, String description, TestClosureParams3<P1, P2, P3> testBody) {
        return coreDslBuilder.ignoredParametrizedTestExpectingException3(expectedException,
                shouldThrowDescription(expectedException, description), testBody);
    }
}
