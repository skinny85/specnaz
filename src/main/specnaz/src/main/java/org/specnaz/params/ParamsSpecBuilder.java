package org.specnaz.params;

import org.specnaz.SpecBuilder;

public interface ParamsSpecBuilder extends SpecBuilder {
    <P> ParamsExpected1<P> should(String description, TestClosureParams1<P> testBody);
}
