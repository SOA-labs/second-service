package ru.artemiyandstepan.secondservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SecondServiceApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SecondServiceApplication.class, args);
    }

}
