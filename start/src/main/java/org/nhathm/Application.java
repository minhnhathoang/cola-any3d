package org.nhathm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.ylzl.eden.spring.framework.web.rest.annotation.EnableRestExceptionHandler;

/**
 * Spring Boot Starter
 */
@EnableRestExceptionHandler
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
