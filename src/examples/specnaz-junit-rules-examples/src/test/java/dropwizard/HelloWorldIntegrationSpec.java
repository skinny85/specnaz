package dropwizard;

import dropwizard.api.Saying;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.specnaz.junit.SpecnazJUnit;
import org.specnaz.junit.rules.Rule;

import javax.ws.rs.client.Client;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Taken from <a href="https://nbsoftsolutions.com/blog/getting-started-with-dropwizard-testing#integration-testing">here</a>.
 */
public class HelloWorldIntegrationSpec extends SpecnazJUnit {
    public Rule<DropwizardAppRule<HelloWorldConfiguration>> appRule = Rule.of(() ->
            new DropwizardAppRule<>(HelloWorldApplication.class,
                    ResourceHelpers.resourceFilePath("hello-world.yml")));

    {
        describes("The Dropwizard HelloWorld application", it -> {
            it.should("greet 'dropwizard' correctly", () -> {
                Client client = new JerseyClientBuilder().build();
                Saying result = client.target(
                        String.format("http://localhost:%d/hello-world", appRule.r().getLocalPort())
                ).queryParam("name", "dropwizard").request().get(Saying.class);

                assertThat(result.getContent()).isEqualTo("Hello, dropwizard!");
            });
        });
    }
}
