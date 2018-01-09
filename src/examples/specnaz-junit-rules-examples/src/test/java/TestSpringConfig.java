import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
