import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@PropertySource( "application.properties")
@ComponentScan
public class ICSApplication {
    public static void main (String[] args){

//        ApplicationContext context = new ClassPathXmlApplicationContext("application.properties");
        SpringApplication.run(ICSApplication.class, args);
    }
}
