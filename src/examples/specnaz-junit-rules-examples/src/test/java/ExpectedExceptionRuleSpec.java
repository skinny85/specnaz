import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.specnaz.Specnaz;
import org.specnaz.junit.rules.Rule;
import org.specnaz.junit.rules.SpecnazJUnitRunner2_Rules;

@RunWith(SpecnazJUnitRunner2_Rules.class)
public class ExpectedExceptionRuleSpec implements Specnaz {
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
