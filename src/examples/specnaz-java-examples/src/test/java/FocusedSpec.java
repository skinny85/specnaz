import org.specnaz.junit.SpecnazJUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * This example shows the usage of 'focused' methods -
 * {@link org.specnaz.SpecBuilder#fshould} and {@link org.specnaz.SpecBuilder#fshouldThrow}.
 */
public class FocusedSpec extends SpecnazJUnit {{
    describes("A spec with focused tests", it -> {
        it.fshould("execute a focused test", () -> {
            assertThat(1 + 1).isEqualTo(2);
        });

        it.should("ignore a non-focused test", () -> {
            fail("this unfocused test should not have been executed");
        });

        it.describes("and a subgroup with focused tests", () -> {
            it.fshouldThrow(IndexOutOfBoundsException.class, "when executing a focused test in the subgroup",  () -> {
                int unused = new int[]{1, 2, 3}[3];
            });
        });

        it.describes("and a subgroup without focused tests", () -> {
            it.beginsAll(() -> {
                fail("this beginsAll should not have been executed");
            });

            it.should("not ran either the test, nor any fixture in that group", () -> {
                fail("this test should not have been executed");
            });

            it.endsAll(() -> {
                fail("this endsEach should not have been executed");
            });
        });
    });
}}
