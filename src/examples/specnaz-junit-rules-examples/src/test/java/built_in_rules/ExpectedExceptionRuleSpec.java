package built_in_rules;

import org.junit.rules.ExpectedException;
import org.specnaz.junit.SpecnazJUnit;
import org.specnaz.junit.rules.Rule;
import org.specnaz.utils.TestClosure;

/**
 * An example of using Specnaz with the built-in {@link ExpectedException} Rule.
 *
 * @see org.specnaz.SpecBuilder#shouldThrow
 */
public class ExpectedExceptionRuleSpec extends SpecnazJUnit {
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
