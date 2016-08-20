import org.specnaz.junit.SpecnazJUnit;
import org.specnaz.utils.IntBox;

import static org.assertj.core.api.Assertions.assertThat;
import static org.specnaz.utils.IntBox.boxWith;

public class BasicSpecnazSpec extends SpecnazJUnit {{
    describes("arithmetic functions", it -> {
        IntBox two = boxWith(2);

        it.beginsEach(() -> {
            two._++;
        });

        it.beginsEach(() -> {
            two._++;
        });

        it.should("correctly add two numbers", () -> {
            assertThat(two._ + 2).isEqualTo(4);
        });

        it.should("correctly subtract two numbers", () -> {
            assertThat(two._ - 2).isZero();
        });

        it.endsEach(() -> {
            two._--;
        });

        it.endsEach(() -> {
            two._--;
        });
    });
}}
