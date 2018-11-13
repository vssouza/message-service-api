package com.example.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties
public class MessageServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageServiceApiApplication.class, args);
    }
}
