package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExampleTest {
    @BeforeAll
    public static void beforeAll() throws InterruptedException {
        Thread.sleep(5_000);
    }

    @BeforeEach
    void setUp() throws InterruptedException {
        Thread.sleep(5_000);
    }

    @Test
    void example_test() {
//        Assertions.fail();
    }
}
