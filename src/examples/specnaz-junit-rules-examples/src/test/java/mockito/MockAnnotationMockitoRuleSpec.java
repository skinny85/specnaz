package mockito;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.specnaz.junit.SpecnazJUnit;
import org.specnaz.junit.rules.Rule;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * A test showing how to use the {@link MockitoRule} to populate fields
 * annotated with {@link Mock}.
 */
public class MockAnnotationMockitoRuleSpec extends SpecnazJUnit {
    // Mockito Rules are not meant to be used
    @SuppressWarnings("unused")
    public Rule<MockitoRule> mockitoRule = Rule.of(() -> MockitoJUnit.rule());

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
        });
    }
}
