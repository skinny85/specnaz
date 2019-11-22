package com.example;

import org.junit.platform.commons.annotation.Testable;
import org.specnaz.Specnaz;
import org.specnaz.utils.Box;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@Testable
public class ExampleSpecnazTest implements Specnaz {{
    Box<String> str = Box.boxWith("");

    describes("root describe", it -> {
        it.beginsAll(() -> {
            str.$ += "a";
        });

        it.endsAll(() -> {
//            fail("endsAll");
        });

        it.beginsEach(() -> {
            str.$ += "b";
        });

        it.should("execute this test", () -> {
            assertThat(str.$.length()).isGreaterThan(1);
        });

        it.should("also execute this test", () -> {
            assertThat(str.$.charAt(0)).isEqualTo('a');
        });

        it.describes("with a subgroup", () -> {
            it.should("execute the subgroup tests", () -> {
                assertThat(str.$).matches("ab+ab*");
            });

            it.describes("with a sub-sub group", () -> {
                it.should("execute the sub-sub group test", () -> {
                    assertThat(str.$).matches("ab+ab+ab*");
                });
            });
        });
    });
}}
