import org.specnaz.junit.SpecnazJUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicSpecnazSpec extends SpecnazJUnit {{
    describes("arithmetic functions", it -> {
        int[] two = {2};

        it.beginsEach(() -> {
            two[0]++;
        });

        it.beginsEach(() -> {
            two[0]++;
        });

        it.should("correctly add two numbers", () -> {
            assertThat(two[0] + 2).isEqualTo(4);
        });

        it.should("correctly subtract two numbers", () -> {
            assertThat(two[0] - 2).isZero();
        });

        it.endsEach(() -> {
            two[0]--;
        });

        it.endsEach(() -> {
            two[0]--;
        });
    });
}}
