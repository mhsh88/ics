import com.payAm.core.model.BaseEntity;
import daos.assessments.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication(scanBasePackages={"com.*","controllers.*","models.*", "repositories.*","service.*","daos.*","config"})//{"models","controllers", "com.payAm.core.ebean"})
@EnableJpaAuditing
@PropertySource("application.properties")
@ComponentScan(basePackages = {"com", "controllers","com.payAm.core.model","config"})
@EntityScan(basePackages ={"com.*","controllers.*","models.*","repositories.*","service.*","com.payAm.core.model.*","daos.*","config"},basePackageClasses = BaseEntity.class)
@EnableJpaRepositories(basePackages = {"com.*","controllers.*","models.*", "repositories.*","service.*","daos.*","config"}/*, entityManagerFactoryRef="emf"*/)
public class ICSApplication {
    public static void main (String[] args){

//        ApplicationContext context = new ClassPathXmlApplicationContext("application.properties");
        SpringApplication.run(ICSApplication.class, args);
    }


//        @Bean
//        public FilterRegistrationBean corsFilter() {
//            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//            CorsConfiguration config = new CorsConfiguration();
//            config.setAllowCredentials(true);
//            config.addAllowedOrigin("*");
//            config.addAllowedHeader("*");
//            config.addAllowedMethod("*");
//            source.registerCorsConfiguration("/**", config);
//            FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//            bean.setOrder(0);
//            return bean;
//        }

//    @Bean
//    public BaseService serviceMapper() {
//        return new Service();
//    }

//    @Bean
//    public SalService salServiceMapper(){
//        return new SalService();
//    }
//
//    @Bean
//    public AssessmentSalService assessmentSalServiceMapper(){
//        return new AssessmentSalService();
//    }


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

//class Service extends BaseService {
//
//}



