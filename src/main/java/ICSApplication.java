import com.fasterxml.jackson.databind.ser.Serializers;
import com.payAm.core.ebean.BaseDAORepository;
import com.payAm.core.ebean.BaseService;
import com.payAm.core.model.BaseEntity;
import org.springframework.beans.factory.FactoryBean;
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
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication(scanBasePackages={"com.*","controllers.*","models.*","dao.*","service.*"})//{"models","controllers", "com.payAm.core.ebean"})
@EnableJpaAuditing
@Configuration
@PropertySource("application.properties")
@ComponentScan(basePackages = {"com", "controllers","com.payAm.core.model.*"})
@EntityScan(basePackages ={"com.*","controllers.*","models.*","dao.*","service.*","com.payAm.core.model.*"},basePackageClasses = BaseEntity.class) //{"models"},basePackageClasses = BaseEntity.class)
@EnableJpaRepositories(basePackages = {"com.*","controllers.*","models.*","dao.*","service.*"})//basePackageClasses = {BaseDAORepository.class, BaseService.class})
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

//    @Bean
//    public FactoryBean<EntityManagerFactory> entityManagerFactory() {
//    ...
//
//        entityManagerFactoryBean.setJpaProperties(props);
//        return entityManagerFactoryBean;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
}

class Service extends BaseService {

}

class SalService extends service.assessments.SalService{

}
