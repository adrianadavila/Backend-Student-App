package hello;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.HashMap;

@SpringBootApplication
@EnableResourceServer
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);


    }
}
