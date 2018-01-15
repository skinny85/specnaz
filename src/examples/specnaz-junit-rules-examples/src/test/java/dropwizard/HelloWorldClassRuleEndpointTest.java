package dropwizard;

import com.fasterxml.jackson.databind.ObjectReader;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldClassRuleEndpointTest {
    @ClassRule
    public static ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new HelloWorldResource("Hello, %s!", "Stranger"))
            .build();

    @Test
    public void hello_world_dropwizard() throws IOException {
        // Hit the endpoint and get the raw json string
        String resp = resources.client()
                .target("/hello-world")
                .queryParam("name", "dropwizard")
                .request()
                .get(String.class);

        // The expected json that will be returned
        String json = "{ \"id\": 1, \"content\": \"Hello, dropwizard!\" }";

        // The object responsible for translating json to our class
        ObjectReader reader = resources.getObjectMapper().readerFor(Saying.class);

        // Deserialize our actual and expected responses
        Saying actual = reader.readValue(resp);
        Saying expected = reader.readValue(json);

        assertThat(actual.getId())
                .isEqualTo(expected.getId())
                .isEqualTo(1);

        assertThat(actual.getContent())
                .isEqualTo(expected.getContent())
                .isEqualTo("Hello, dropwizard!");
    }

    @Test
    public void hello_world_absent_name() {
        // A more terse way to test just an endpoint
        Saying actual = resources.client().target("/hello-world")
                .request().get(Saying.class);

        // because we're using a @ClassRule now,
        // the counter doesn't get re-set between the tests
        assertThat(actual.getId()).isEqualTo(2);
        assertThat(actual.getContent()).isEqualTo("Hello, Stranger!");
    }
}
