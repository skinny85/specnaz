package params;

import org.specnaz.params.junit.SpecnazParamsJUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class ParamsAddSpec extends SpecnazParamsJUnit {
    {
        describes("a parametrized add spec", it -> {
            it.should("test adding correctly", (Integer x) -> {
                assertThat(x).isNegative();
            }).provided(1, 2, 3);
        });
    }
}
