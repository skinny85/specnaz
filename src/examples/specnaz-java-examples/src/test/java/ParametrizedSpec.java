import org.specnaz.params.junit.SpecnazParamsJUnit;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.specnaz.params.Params2.p;
import static org.specnaz.params.Params3.p;

/**
 * An example of a parametrized Specnaz spec.
 */
public class ParametrizedSpec extends SpecnazParamsJUnit {
    {
        describes("A parametrized spec", it -> {
            it.should("confirm that %1 + %2 = %3", (Integer a, Integer b, Integer c) -> {
                assertThat(a + b).isEqualTo(c);
            }).provided(
                    p(1, 2, 3),
                    p(4, 4, 8),
                    p(-3, 3, 0),
                    p(Integer.MAX_VALUE, 1, Integer.MIN_VALUE)
            );

            it.should("correctly parse '%1' as an Int with radix %2", (String str, Integer radix) -> {
                assertThat(Integer.parseInt(str, radix)).isPositive();
            }).provided(
                    p("cafe", 16),
                    p("G", 17)
            );

            it.describes("with '%1' as the argument", (String str) -> {
                it.shouldThrow(NumberFormatException.class,
                        format("when trying to parse '%s' as an Int with radix %%1", str), (Integer radix) -> {
                            Integer.parseInt(str, radix);
                        }).provided(8, 10)
                        .withMessageContaining(str);
            }).provided("cafe", "G");
        });
    }
}
