package spring;

import a.DaoA;
import a.ServiceA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A test Spring configuration,
 * wiring up {@link TestDaoA} as the implementation of {@link DaoA}.
 */
@Configuration
public class TestSpringConfig {
    @Bean
    public DaoA daoA() {
        return new TestDaoA();
    }

    @Bean
    public ServiceA serviceA(DaoA daoA) {
        return new ServiceA(daoA);
    }
}
