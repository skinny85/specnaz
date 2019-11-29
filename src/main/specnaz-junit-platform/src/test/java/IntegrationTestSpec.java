import org.junit.platform.commons.annotation.Testable;
import org.specnaz.params.SpecnazParams;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@Testable
public class IntegrationTestSpec implements SpecnazParams {{
    describes("Specnaz JUnit Platform test engine", it -> {
        it.xshould("not execute ignored tests", () -> {
            fail("xshould");
        });

        it.describes("handles empty subgroups correctly", () -> {
            // intentionally left empty
        });

        it.should("work with parametrized tests (%1 > 0)", (Integer i) -> {
            assertThat(i).isGreaterThan(0);
        }).provided(1, 2);
    });
}}
