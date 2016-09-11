package org.specnaz.examples.custom_dsl.given_when_then;

import org.specnaz.utils.TestClosure;

public interface ThensBuilder {
    void then(String description, TestClosure testBody);
}
