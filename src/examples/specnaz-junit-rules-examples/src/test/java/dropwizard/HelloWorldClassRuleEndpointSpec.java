package dropwizard;

import com.fasterxml.jackson.databind.ObjectReader;
import dropwizard.api.Saying;
import dropwizard.resources.HelloWorldResource;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.specnaz.junit.SpecnazJUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Same as {@link HelloWorldEndpointSpec},
 * but uses a {@link ClassRule} instead of an instance Rule.
 * That means that the server is started only once before all tests,
 * not re-started before each one -
 * which might be faster if you're not doing mutating operations.
 */
public class HelloWorldClassRuleEndpointSpec extends SpecnazJUnit {
    @ClassRule
    public static ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new HelloWorldResource("Hello, %s!", "Stranger"))
            .build();

    {
        describes("The HelloWorld endpoint", it -> {
            it.should("greet 'dropwizard' correctly", () -> {
                String resp = resources.client()
                        .target("/hello-world")
                        .queryParam("name", "dropwizard")
                        .request()
                        .get(String.class);

                String json = "{ \"id\": 1, \"content\": \"Hello, dropwizard!\" }";

                ObjectReader reader = resources.getObjectMapper().readerFor(Saying.class);

                Saying actual = reader.readValue(resp);
                Saying expected = reader.readValue(json);

                assertThat(actual.getId())
                        .isEqualTo(expected.getId())
                        .isEqualTo(1);

                assertThat(actual.getContent())
                        .isEqualTo(expected.getContent())
                        .isEqualTo("Hello, dropwizard!");
            });

            it.should("default the greeting to 'Stranger'", () -> {
                Saying actual = resources.client().target("/hello-world")
                        .request().get(Saying.class);

                // because we're using a @ClassRule now,
                // the counter doesn't get re-set between the tests
                assertThat(actual.getId()).isEqualTo(2);
                assertThat(actual.getContent()).isEqualTo("Hello, Stranger!");
            });
        });
    }
}
