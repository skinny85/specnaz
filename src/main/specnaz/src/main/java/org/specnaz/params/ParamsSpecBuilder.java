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
}
