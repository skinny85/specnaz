package junit.rules;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.specnaz.Specnaz;
import org.specnaz.junit.rules.MethodRuleHolder;
import org.specnaz.junit.rules.SpecnazJUnitRunner2_Rules;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpecnazJUnitRunner2_Rules.class)
public class MockitoRuleSpec implements Specnaz {
    @Rule
    public MethodRuleHolder<MockitoRule> mockitoRule = new MethodRuleHolder<>(() -> MockitoJUnit.rule().silent());

    @Mock
    List<Integer> listMock;

    {
        describes("Using the JUnit Mockito Rule in Specnaz", it -> {
            it.should("initialize fields annotated with @Mock", () -> {
                assertThat(listMock).isNotNull();

                when(listMock.get(0)).thenReturn(400 + 56);

                assertThat(listMock.get(0)).isEqualTo(456);
            });

            it.should("reset the mocks after every test", () -> {
                assertThat(listMock.get(0)).isNull();
            });

            it.should("still add correctly", () -> {
                assertThat(1 + 2).isEqualTo(3);
            });

            it.describes("with a subgroup without any tests in it", () -> {
                it.describes("but with another subgroup, which does have tests", () -> {
                    it.should("not explode", () -> {
                        assertThat(4 + 5).isEqualTo(9);
                    });
                });
            });
        });
    }
}
