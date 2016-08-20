import org.specnaz.junit.SpecnazJUnit;
import org.specnaz.utils.IntBox;

import static org.assertj.core.api.Assertions.assertThat;
import static org.specnaz.utils.IntBox.boxWith;

public class BasicSpecnazSpec extends SpecnazJUnit {{
    describes("arithmetic operations", it -> {
        IntBox two = boxWith(-2);

        it.beginsAll(() -> {
            two._ += 2;
        });

        it.beginsEach(() -> {
            two._++;
        });

        it.beginsEach(() -> {
            two._++;
        });

        it.should("sum two numbers correctly", () -> {
            assertThat(two._ + 2).isEqualTo(4);
        });

        it.should("subtract two numbers correctly", () -> {
            assertThat(two._ - 2).isZero();
        });

        it.describes("with a subgroup", () -> {
            it.beginsAll(() -> {
                two._ += 3;
            });

            it.should("run all parent 'before' callbacks", () -> {
                assertThat(two._).isEqualTo(5);
            });

            it.describes("and a third-degree subgroup", () -> {
                it.beginsEach(() -> {
                    two._ += 4;
                });

                it.should("run all ancestors 'before' callbacks", () -> {
                    assertThat(two._).isEqualTo(9);
                });

                it.endsEach(() -> {
                    two._ -= 4;
                });

                it.describes("with a subgroup without tests", () -> {

                });
            });

            it.endsAll(() -> {
                two._ -= 3;
            });
        });

        it.endsEach(() -> {
            two._--;
        });

        it.endsEach(() -> {
            two._--;
        });

        it.endsAll(() -> {
            assertThat(two._).isZero();
            two._ -= 2;
        });
    });
}}
