import org.specnaz.params.testng.SpecnazParamsFactoryTestNG;
import org.testng.annotations.Test;

import static java.lang.String.format;

@Test
public class ParametrizedSpec implements SpecnazParamsFactoryTestNG {{
    describes("A parametrized spec", it -> {
        it.describes("with '%1' as the argument", (String str) -> {
            it.shouldThrow(NumberFormatException.class,
                format("when trying to parse '%s' as an Int with radix %%1", str), (Integer radix) -> {
                    Integer.parseInt(str, radix);
            }).provided(8, 10)
            .withMessageContaining(str);
        }).provided("cafe", "G");
    });
}}
