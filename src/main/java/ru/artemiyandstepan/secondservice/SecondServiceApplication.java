package ru.artemiyandstepan.secondservice;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@SpringBootApplication
public class SecondServiceApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SecondServiceApplication.class, args);
    }

    @Component
    public class LoggingFilter extends OncePerRequestFilter {
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            long startTime = System.currentTimeMillis();
            // Продолжить обработку запроса
            filterChain.doFilter(request, response);
            long duration = System.currentTimeMillis() - startTime;
            // Создание записи лога
            String logMessage = String.format("request method: %s, request URI: %s, response status: %d, request processing time: %d ms", request.getMethod(), request.getRequestURI(), response.getStatus(), duration);
            logger.info(logMessage);
        }
    }
}
