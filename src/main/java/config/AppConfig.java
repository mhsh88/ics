package config;

import daos.assessments.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Lazy
@Configuration
public class AppConfig {

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

}
