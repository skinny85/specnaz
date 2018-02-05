package params;

import org.specnaz.params.junit.SpecnazParamsJUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class ParamsAddSpec extends SpecnazParamsJUnit {
    {
        describes("a parametrized add spec", it -> {
            it.should("test that %1 is positive", (Integer x) -> {
                assertThat(x).isPositive();
            }).provided(1, 2, 3);
        });
    }
}
