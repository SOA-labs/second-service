package ru.artemiyandstepan.secondservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SecondServiceApplication extends SpringBootServletInitializer {

    private static final Class<SecondServiceApplication> applicationClass = SecondServiceApplication.class;

    public static void main(String[] args) {
        SpringApplication.run(SecondServiceApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
}
