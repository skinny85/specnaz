package spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestSpringConfig.class)
public class SpringIntegrationTest {
    @Autowired
    ServiceA serviceA;

    @Test
    void test_service_a() {
        assertThat(serviceA).isNotNull();
        assertThat(serviceA.findA()).isEqualTo("ServiceA:TestDaoA");
    }
}
