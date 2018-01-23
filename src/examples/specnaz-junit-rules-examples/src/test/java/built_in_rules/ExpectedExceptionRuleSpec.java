package built_in_rules;

import org.junit.rules.ExpectedException;
import org.specnaz.junit.SpecnazJUnit2_Rules;
import org.specnaz.junit.rules.Rule;

public class ExpectedExceptionRuleSpec extends SpecnazJUnit2_Rules {
    public Rule<ExpectedException> expectedException = Rule.of(() -> ExpectedException.none());

    {
        describes("Using the ExpectedException JUnit Rule in Specnaz", it -> {
            it.should("correctly set the expected Exception", () -> {
                expectedException.r().expect(IllegalArgumentException.class);

                throw new IllegalArgumentException();
            });

            it.should("correctly re-set the Rule after the first test", () -> {
                expectedException.r().handleAssertionErrors();
            });
        });
    }
}
