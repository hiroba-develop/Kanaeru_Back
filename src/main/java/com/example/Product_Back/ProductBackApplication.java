package com.example.Product_Back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProductBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductBackApplication.class, args);
    }
}