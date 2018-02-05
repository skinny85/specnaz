package org.specnaz.params;

import org.specnaz.SpecBuilder;

public interface ParamsSpecBuilder extends SpecBuilder {
    <T> ParamsExpected1<T> should(String description, TestClosureParams1<T> testBody);
}
