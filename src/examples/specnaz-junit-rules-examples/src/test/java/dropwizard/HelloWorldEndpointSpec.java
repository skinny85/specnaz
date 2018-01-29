package dropwizard;

import com.fasterxml.jackson.databind.ObjectReader;
import dropwizard.api.Saying;
import dropwizard.resources.HelloWorldResource;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.specnaz.junit.SpecnazJUnit;
import org.specnaz.junit.rules.Rule;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * Taken from <a href="https://nbsoftsolutions.com/blog/getting-started-with-dropwizard-testing#endpoint-testing">here</a>.
 */
public class HelloWorldEndpointSpec extends SpecnazJUnit {
    public final Rule<ResourceTestRule> resources = Rule.of(() -> ResourceTestRule.builder()
            .addResource(new HelloWorldResource("Hello, %s!", "Stranger"))
            .build());

    {
        describes("The HelloWorld endpoint", it -> {
            it.should("greet 'dropwizard' correctly", () -> {
                // Hit the endpoint and get the raw json string
                String resp = resources.r().client()
                        .target("/hello-world")
                        .queryParam("name", "dropwizard")
                        .request()
                        .get(String.class);

                // The expected json that will be returned
                String json = "{ \"id\": 1, \"content\": \"Hello, dropwizard!\" }";

                // The object responsible for translating json to our class
                ObjectReader reader = resources.r().getObjectMapper().readerFor(Saying.class);

                // Deserialize our actual and expected responses
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
                // A more terse way to test just an endpoint
                Saying actual = resources.r().client().target("/hello-world")
                        .request().get(Saying.class);

                assertThat(actual.getId()).isEqualTo(1);
                assertThat(actual.getContent()).isEqualTo("Hello, Stranger!");
            });
        });
    }
}
