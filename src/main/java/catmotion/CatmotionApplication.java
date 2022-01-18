package catmotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CatmotionApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CatmotionApplication.class, args);
    }

}