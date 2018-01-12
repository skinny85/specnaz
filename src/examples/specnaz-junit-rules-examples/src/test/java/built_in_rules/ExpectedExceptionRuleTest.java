package built_in_rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExpectedExceptionRuleTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void test_expected_exception_throwing() throws Exception {
        expectedException.expect(IllegalArgumentException.class);

        throw new IllegalArgumentException();
    }

    @Test
    public void test_expected_exception_not_throwing() throws Exception {
        expectedException.handleAssertionErrors();
    }
}
