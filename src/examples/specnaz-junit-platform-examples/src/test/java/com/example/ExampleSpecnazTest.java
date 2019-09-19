package com.example;

import org.junit.jupiter.api.Assertions;
import org.specnaz.Specnaz;

public class ExampleSpecnazTest implements Specnaz {{
    describes("root describe", it -> {
        it.should("execute this test", () -> {
            Assertions.fail();
        });

        it.should("also execute this test", () -> {
            Assertions.fail();
        });

        it.describes("with a subgroup", () -> {
            it.should("execute the subgroup tests", () -> {
                Assertions.fail();
            });
        });
    });
}}
