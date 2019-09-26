package com.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ExampleTest {
    @BeforeAll
    static void beforeAll() throws InterruptedException {
        Thread.sleep(5_000);
    }

    @BeforeEach
    void setUp() throws InterruptedException {
        Thread.sleep(5_000);
    }

    @Test
    void example_test() {
//        fail();
    }
}
