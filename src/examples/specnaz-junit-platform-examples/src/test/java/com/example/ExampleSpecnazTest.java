package com.example;

import org.junit.jupiter.api.Assertions;
import org.specnaz.Specnaz;

public class ExampleSpecnazTest implements Specnaz {{
    describes("root describe", it -> {
        it.should("add this test", () -> {
//            Assertions.fail();
        });
    });
}}
