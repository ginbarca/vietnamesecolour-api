package au.com.vietnamesecolour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class VietnameseColourApplication {

    public static void main(final String[] args) {
        SpringApplication.run(VietnameseColourApplication.class, args);
    }

}
