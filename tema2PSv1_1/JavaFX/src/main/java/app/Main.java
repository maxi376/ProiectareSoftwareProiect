package app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@CrossOrigin
@ComponentScan(basePackages = {
        "utils",
        "controller",
        "model",
        "services"
})
@EnableWebMvc
@EnableJpaRepositories(basePackages = {"repositories"})
@SpringBootApplication()
@EnableScheduling
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);



    }

}