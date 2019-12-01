import org.junit.runner.RunWith;
import org.specnaz.Specnaz;
import org.specnaz.junit.SpecnazJUnitRunner;
import org.specnaz.utils.IntBox;

import static org.assertj.core.api.Assertions.assertThat;
import static org.specnaz.utils.IntBox.boxWith;

/**
 * This spec illustrates how are the various fixtures
 * ({@code begins/ends}) executed.
 * It also doesn't inherit from {@link org.specnaz.junit.SpecnazJUnit},
 * instead setting the JUnit 4 Runner explicitly with the {@link RunWith} annotation.
 * <p>
 * Addiotionally, it demonstrates the usage of a {@link org.specnaz.utils.Box}
 * (more specifically, an {@link IntBox}) to get around Java's
 * 'variables referenced in lambdas must be effectively final' restriction.
 */
@RunWith(SpecnazJUnitRunner.class)
public class ArithmeticSpec implements Specnaz {{
    describes("arithmetic operations", it -> {
        IntBox two = boxWith(-2);

        it.beginsAll(() -> {
            two.$ += 2;
        });

        it.beginsEach(() -> {
            two.$++;
        });

        it.beginsEach(() -> {
            two.$++;
        });

        it.should("sum two numbers correctly", () -> {
            assertThat(two.$ + 2).isEqualTo(4);
        });

        it.should("subtract two numbers correctly", () -> {
            assertThat(two.$ - 2).isZero();
        });

        it.shouldThrow(ArithmeticException.class, "when dividing by zero", () -> {
            int unused = 1 / 0;
        }).withMessage("/ by zero").withoutCause();

        it.describes("with a subgroup", () -> {
            it.beginsAll(() -> {
                two.$ += 3;
            });

            it.should("run all parent 'before' callbacks", () -> {
                assertThat(two.$).isEqualTo(5);
            });

            it.describes("and a third-degree subgroup", () -> {
                it.beginsEach(() -> {
                    two.$ += 4;
                });

                it.should("run all ancestors 'before' callbacks", () -> {
                    assertThat(two.$).isEqualTo(9);
                });

                it.endsEach(() -> {
                    two.$ -= 4;
                });

                it.describes("with a subgroup without tests", () -> {

                });
            });

            it.endsAll(() -> {
                two.$ -= 3;
            });
        });

        it.endsEach(() -> {
            two.$--;
        });

        it.endsEach(() -> {
            two.$--;
        });

        it.endsAll(() -> {
            assertThat(two.$).isZero();
            two.$ -= 2;
        });
    });
}}
