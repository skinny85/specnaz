package org.specnaz.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class SpecnazTestNgSpecWithBefores implements SpecnazFactoryTestNG {
    int counter = 0;

    {
        describes("A spec with befores", it -> {
            it.beginsAll(() -> {
                counter++;
            });

            it.beginsEach(() -> {
                counter++;
            });

            it.should("run the beginsEach to increment the counter by 1", () -> {
                Assert.assertEquals(counter, 2);
            });

            it.should("also run the endsEach to decrement the counter by 1 ", () -> {
                Assert.assertEquals(counter, 2);
            });

            it.shouldThrow(ArithmeticException.class, "when dividing by counter - 2", () -> {
                int unused = 1 / (counter - 2);
            });

            it.endsEach(() -> {
                counter--;
            });

            it.endsAll(() -> {
                counter--;
            });

            it.describes("with a sub-specification", () -> {
                it.beginsAll(() -> {
                    counter++;
                });

                it.beginsEach(() -> {
                    counter++;
                });

                it.should("run the parent fixtures in the child test", () -> {
                    Assert.assertEquals(counter, 4);
                });

                it.endsEach(() -> {
                    counter--;
                });

                it.endsAll(() -> {
                    counter--;
                });
            });
        });
    }
}
