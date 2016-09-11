package org.specnaz.examples.custom_dsl.given_when_then;

import org.specnaz.core.CoreDslBuilder;
import org.specnaz.utils.TestClosure;

import java.util.function.Consumer;

public class GivenWhenThenBuilder implements GivenBuilder, ThensBuilder {
    private final CoreDslBuilder coreDslBuilder;

    public GivenWhenThenBuilder(CoreDslBuilder coreDslBuilder) {
        this.coreDslBuilder = coreDslBuilder;
    }

    @Override
    public void given(String description, TestClosure action, Runnable closure) {
        coreDslBuilder.subSpecification(description, () -> {
            coreDslBuilder.beforeAll(action);

            closure.run();
        });
    }

    @Override
    public void when(String description, TestClosure action, Consumer<ThensBuilder> thens) {
        coreDslBuilder.subSpecification("when " + description, () -> {
            coreDslBuilder.beforeAll(action);

            thens.accept(this);
        });
    }

    @Override
    public void then(String description, TestClosure testBody) {
        coreDslBuilder.test("then " + description, testBody);
    }
}
