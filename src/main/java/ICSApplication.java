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

@SpringBootApplication(scanBasePackages={"com.*","controllers.*","models.*", "repositories.*","service.*","daos.*"})//{"models","controllers", "com.payAm.core.ebean"})
@EnableJpaAuditing
@Configuration
@PropertySource("application.properties")
@ComponentScan(basePackages = {"com", "controllers","com.payAm.core.model"})
@EntityScan(basePackages ={"com.*","controllers.*","models.*","repositories.*","service.*","com.payAm.core.model.*","daos.*"},basePackageClasses = BaseEntity.class) //{"models"},basePackageClasses = BaseEntity.class)
@EnableJpaRepositories(basePackages = {"com.*","controllers.*","models.*", "repositories.*","service.*","daos.*"})//basePackageClasses = {BaseDAORepository.class, BaseService.class})
public class ICSApplication {
    public static void main (String[] args){

//        ApplicationContext context = new ClassPathXmlApplicationContext("application.properties");
        SpringApplication.run(ICSApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
    @Bean
    public AssessmentSalDao assessmentSalDaoBean(){
        return new AssessmentSalDao();
    }
    @Bean
    public DatabaseQuestionDao databaseQuestionDao() {
        return new DatabaseQuestionDao();
    }
    @Bean
    public MetricDao metricDao(){
        return new MetricDao();
    }
    @Bean
    public OrganizationAssessmentDao organizationAssessmentDao(){
        return new OrganizationAssessmentDao();
    }
    @Bean
    public OrganizationAssessmentHasQuestionDao organizationAssessmentHasQuestionDao(){
        return new OrganizationAssessmentHasQuestionDao();
    }
    @Bean
    public PreQuestionAnswerDao preQuestionAnswerDao(){
        return new PreQuestionAnswerDao();
    }
    @Bean
    public PreQuestionDao preQuestionDao(){
        return new PreQuestionDao();
    }
    @Bean
    public QuestionAnswerDao questionAnswerDao(){
        return new QuestionAnswerDao();
    }

    @Bean
    public QuestionDao questionDao(){
        return new QuestionDao();
    }
    @Bean
    public QuestionHasSalDao questionHasSalDao(){
        return new QuestionHasSalDao();
    }
    @Bean
    public QuestionScopeDao questionScopeDao(){
        return new QuestionScopeDao();
    }
    @Bean
    public StandardDao standardDao(){
        return new StandardDao();
    }
    @Bean
    public SalDao salDao(){
        return new SalDao();
    }
    @Bean
    public SubMetricDao subMetricDao(){
        return new SubMetricDao();
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



