import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@ContextConfiguration(classes = TestSpringConfig.class)
public class SpringIntegrationTest {
    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    ServiceA serviceA;

    @Test
    public void test_method() {
        assertThat(serviceA.findA()).isEqualTo("ServiceA:TestDaoA");
    }

    @Test
    @IfProfileValue(name="test_prop")
    public void test_if_profile_value() throws Exception {
        fail("this should not be executed");
    }
}
