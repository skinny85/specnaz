package mockito;

import a.DaoA;
import a.ServiceA;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.specnaz.junit.SpecnazJUnit;
import org.specnaz.junit.rules.Rule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Shows how the {@link MockitoRule} allows you to leverage the {@link InjectMocks}
 * annotation.
 * <p>
 * There is one subtlety that needs attention: fields annotated with {@link InjectMocks}
 * need to be re-set after each test in a {@link org.specnaz.SpecBuilder#endsEach} method
 * (see below for an explanation why).
 */
public class InjectMocksAnnotationMockitoRuleSpec extends SpecnazJUnit {
    // Mockito Rules are not meant to be used
    @SuppressWarnings("unused")
    public Rule<MockitoRule> mockitoRule = Rule.of(() -> MockitoJUnit.rule());

    @Mock
    DaoA daoA;

    @InjectMocks
    ServiceA serviceA;

    {
        describes("Using the JUnit Mockito Rule in Specnaz", it -> {
            it.should("work correctly with @InjectMocks", () -> {
                when(daoA.getA()).thenReturn("Mock");

                assertThat(serviceA.findA()).isEqualTo("ServiceA:Mock");
            });

            it.should("re-set the injected field in each test", () -> {
                when(daoA.getA()).thenReturn("Mock2");

                assertThat(serviceA.findA()).isEqualTo("ServiceA:Mock2");
            });

            it.endsEach(() -> {
                // Needed in order for the Mockito rule to work correctly -
                // otherwise, the daoA mock will be re-set after the first test,
                // however the serviceA real object will be not (as it's already non-null).
                // The Rule works the way it does because in 'vanilla' JUnit,
                // you get a different instance of the test class for each test,
                // meaning serviceA will be null again.
                // However, in Specnaz, the same instance is re-used for all tests.
                // Comment out the below line and re-run the tests to see the 2nd one fail.
                serviceA = null;
            });
        });
    }
}
