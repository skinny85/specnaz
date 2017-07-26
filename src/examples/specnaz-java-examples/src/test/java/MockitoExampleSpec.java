import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.specnaz.junit.SpecnazJUnit;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * This example shows how to use Specnaz with
 * <a href="http://site.mockito.org/">Mockito</a>.
 */
public class MockitoExampleSpec extends SpecnazJUnit {
    @Mock
    List<Integer> listMock;

    {
        describes("using Mockito @Mock", it -> {
            // if you want to reset the mocks before each test,
            /// use beginsEach instead
            it.beginsAll(() -> {
                MockitoAnnotations.initMocks(this);
            });

            it.should("initialize the @Mocks correctly", () -> {
                assertThat(listMock).isNotNull();
            });

            it.describes("with some stubbing", () -> {
                it.beginsEach(() -> {
                    when(listMock.get(1)).thenReturn(42);
                });

                it.should("return 42 for get(1)", () -> {
                    assertThat(listMock.get(1)).isEqualTo(42);
                });
            });

            it.describes("resets the @Mocks for each group", () -> {
                it.should("return null for get(1)", () -> {
                    assertThat(listMock.get(1)).isNull();
                });
            });
        });
    }
}
