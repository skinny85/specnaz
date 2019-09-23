package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.platform.commons.annotation.Testable;
import org.specnaz.Specnaz;
import org.specnaz.utils.IntBox;

import static org.assertj.core.api.Assertions.assertThat;

@Testable
public class ExampleSpecnazTest implements Specnaz {{
    System.out.println("ExampleSpecnazTest constructor called");

    IntBox i = IntBox.boxWith(0);

    describes("root describe", it -> {
        it.beginsEach(() -> {
            i.$ = 10;
        });

        it.should("execute this test", () -> {
            assertThat(i.$).isGreaterThan(0);
        });

        it.should("also execute this test", () -> {
        });

        it.describes("with a subgroup", () -> {
            it.should("execute the subgroup tests", () -> {
                Assertions.fail("subgroup test");
            });
        });
    });
}}
