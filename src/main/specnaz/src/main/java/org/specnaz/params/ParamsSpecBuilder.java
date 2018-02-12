package org.specnaz.params;

import org.specnaz.SpecBuilder;

public interface ParamsSpecBuilder extends SpecBuilder {
    <P> ParamsExpected1<P> should(String description, TestClosureParams1<P> testBody);
    <T extends Throwable, P> ParamsExpectedException1<T, P> shouldThrow(Class<T> expectedException,
            String description, TestClosureParams1<P> testBody);

    @Deprecated
    <P> ParamsExpected1<P> fshould(String description, TestClosureParams1<P> testBody);
    @Deprecated
    <T extends Throwable, P> ParamsExpectedException1<T, P> fshouldThrow(Class<T> expectedException,
            String description, TestClosureParams1<P> testBody);

    <P> ParamsExpected1<P> xshould(String description, TestClosureParams1<P> testBody);
    <T extends Throwable, P> ParamsExpectedException1<T, P> xshouldThrow(Class<T> expectedException,
            String description, TestClosureParams1<P> testBody);

    <P1, P2> ParamsExpected2<P1, P2> should(String description, TestClosureParams2<P1, P2> testBody);
    <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> shouldThrow(Class<T> expectedException,
            String description, TestClosureParams2<P1, P2> testBody);

    @Deprecated
    <P1, P2> ParamsExpected2<P1, P2> fshould(String description, TestClosureParams2<P1, P2> testBody);
    @Deprecated
    <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> fshouldThrow(Class<T> expectedException,
            String description, TestClosureParams2<P1, P2> testBody);

    <P1, P2> ParamsExpected2<P1, P2> xshould(String description, TestClosureParams2<P1, P2> testBody);
    <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> xshouldThrow(Class<T> expectedException,
            String description, TestClosureParams2<P1, P2> testBody);
}
