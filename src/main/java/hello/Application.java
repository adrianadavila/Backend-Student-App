package hello;

import hello.com.mvc.api.entities.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.HashMap;

@SpringBootApplication
@EnableResourceServer
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);


    }
}
//public class Application extends SpringBootServletInitializer {
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Application.class);
//    }
//
//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(Application.class, args);
//    }
//
//}