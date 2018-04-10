import com.fasterxml.jackson.databind.ser.Serializers;
import com.payAm.core.ebean.BaseDAORepository;
import com.payAm.core.ebean.BaseService;
import com.payAm.core.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com.*","controllers.*","models.*","dao.*","service.*"})//{"models","controllers", "com.payAm.core.ebean"})
@EnableJpaAuditing
@Configuration
@PropertySource("application.properties")
@ComponentScan(basePackages = {"com", "controllers"})
@EntityScan(basePackages ={"com.*","controllers.*","models.*","dao.*","service.*"}) //{"models"},basePackageClasses = BaseEntity.class)
@EnableJpaRepositories(basePackages = {"com.*","controllers.*","models.*","dao.*","service.*"})//basePackageClasses = {BaseDAORepository.class, BaseService.class})
@EnableAutoConfiguration
public class ICSApplication {
    public static void main (String[] args){

//        ApplicationContext context = new ClassPathXmlApplicationContext("application.properties");
        SpringApplication.run(ICSApplication.class, args);
    }

    @Bean
    public BaseService serviceMapper() {
        return new Service();
    }

    @Bean
    public SalService salServiceMapper(){
        return new SalService();
    }
}

class Service extends BaseService {

}

class SalService extends service.assessments.SalService{

}
