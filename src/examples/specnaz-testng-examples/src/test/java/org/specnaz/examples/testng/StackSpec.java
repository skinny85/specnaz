package org.specnaz.examples.testng;

import org.specnaz.testng.SpecnazFactoryTestNG;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Stack;

/**
 * This is the example spec from the Readme file,
 * running using TestNG as the execution engine.
 */
@Test
public class StackSpec implements SpecnazFactoryTestNG {{
    describes("A Stack", it -> {
        Stack<Integer> stack = new Stack<>();

        it.endsEach(() -> {
            stack.clear();
        });

        it.should("be empty when first created", () -> {
            Assert.assertTrue(stack.isEmpty());
        });

        it.describes("with 10 and 20 pushed on it", () -> {
            it.beginsEach(() -> {
                stack.push(10);
                stack.push(20);
            });

            it.should("have size equal to 2", () -> {
                Assert.assertEquals(stack.size(), 2);
            });

            it.should("have 20 as the top element", () -> {
                Assert.assertEquals((int) stack.peek(), 20);
            });
        });
    });
}}
