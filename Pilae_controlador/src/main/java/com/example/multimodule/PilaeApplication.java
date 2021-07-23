package com.example.multimodule;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.example.multimodule.entidad")
@EnableJpaRepositories(basePackages = "com.example.multimodule.infraestructura")
@SpringBootApplication(scanBasePackages = "com.example.multimodule")
@ComponentScan(basePackages = "com.example.multimodule")
public class PilaeApplication {
    public static void main(String[] args) {
        SpringApplication.run(PilaeApplication.class, args);
    }
}
