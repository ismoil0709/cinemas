package uz.pdp.cinemas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CinemasApplication {
    public static void main(String[] args) {
        SpringApplication.run(CinemasApplication.class, args);
    }
}
