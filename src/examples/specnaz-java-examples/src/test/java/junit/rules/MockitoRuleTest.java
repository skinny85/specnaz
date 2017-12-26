package junit.rules;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class MockitoRuleTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Mock
    List<Integer> listMock;

    @Test
    public void test_mockito() throws Exception {
        assertThat(listMock).isNotNull();

        when(listMock.get(0)).thenReturn(456);

        assertThat(listMock.get(0)).isEqualTo(456);
    }

    @Test
    public void every_test_gets_fresh_mocks() throws Exception {
        assertThat(listMock.get(0)).isNull();
    }
}
