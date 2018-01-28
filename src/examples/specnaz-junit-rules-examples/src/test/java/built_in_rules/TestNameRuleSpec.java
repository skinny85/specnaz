package built_in_rules;

import org.junit.rules.TestName;
import org.specnaz.junit.SpecnazJUnit;
import org.specnaz.junit.rules.Rule;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * An example of using Specnaz with the built-in {@link TestName} Rule.
 */
public class TestNameRuleSpec extends SpecnazJUnit {
    public Rule<TestName> testName = Rule.of(() -> new TestName());

    {
        describes("Using the TestName JUnit Rule in Specnaz", it -> {
            it.should("correctly set the test name", () -> {
                assertThat(testName.r().getMethodName()).isEqualTo("should correctly set the test name");
            });
        });
    }
}
