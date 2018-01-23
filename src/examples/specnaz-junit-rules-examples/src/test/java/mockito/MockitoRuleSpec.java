package mockito;

import a.DaoA;
import a.ServiceA;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.specnaz.junit.SpecnazJUnit2_Rules;
import org.specnaz.junit.rules.Rule;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class MockitoRuleSpec extends SpecnazJUnit2_Rules {
    public Rule<MockitoRule> mockitoRule = Rule.of(() -> MockitoJUnit.rule());

    @Mock
    List<Integer> listMock;

    @Mock
    DaoA daoA;

    @InjectMocks
    ServiceA serviceA;

    int count = 0;

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

            it.xshould("still add correctly", () -> {
                assertThat(1 + 2).isEqualTo(3);
            });

            it.describes("with a subgroup without any tests in it", () -> {
                it.beginsAll(() -> {
                    count++;
                });

                it.describes("but with another subgroup, which does have tests", () -> {
                    it.should("not explode", () -> {
                        assertThat(4 + 5).isEqualTo(9);
                    });

                    it.should("run the beginsAll from the parent subgroup, once", () -> {
                        assertThat(count).isEqualTo(1);
                    });

                    it.describes("with another subgroup", () -> {
                        it.should("have a test that passes", () -> {
                        });
                    });
                });

                it.endsAll(() -> {
//                    throw new RuntimeException();
                });
            });

            it.describes("and a second subgroup, on the same level", () -> {
                it.should("pass", () -> {

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

            it.beginsEach(() -> {
//                Thread.sleep(500);
            });
        });
    }
}
