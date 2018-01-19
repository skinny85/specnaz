package mockito;

import a.DaoA;
import a.ServiceA;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.specnaz.Specnaz;
import org.specnaz.junit.rules.Rule;
import org.specnaz.junit.rules.SpecnazJUnitRunner2_Rules;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpecnazJUnitRunner2_Rules.class)
public class MockitoRuleSpec implements Specnaz {
    public Rule<MockitoRule> mockitoRule = Rule.of(() -> MockitoJUnit.rule());

    @Mock
    List<Integer> listMock;

    @Mock
    DaoA daoA;

    @InjectMocks
    ServiceA serviceA;

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

                it.endsAll(() -> {

                });
            });

            it.should("work correctly with @InjectMocks", () -> {
                when(daoA.getA()).thenReturn("Mock");

                assertThat(serviceA.findA()).isEqualTo("ServiceA:Mock");
                // ToDo move this example into its own class
            });

            it.endsEach(() -> {
                // Needed in order for the Mockito rule to work correctly -
                // otherwise, the daoA mock will be re-set, however the serviceA
                // real object will be not (as it's already non-null).
                // The Rule works the way it does because in 'vanilla' JUnit,
                // you get a different instance of the test class for each test,
                // meaning serviceA will be null again.
                // However, in Specnaz, the same instance is re-used for all tests.
                serviceA = null;
            });

            it.endsAll(() -> {
//                throw new RuntimeException();
            });
        });
    }
}
