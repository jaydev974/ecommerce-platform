package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Main entry point for the E-Commerce Platform application.
 * 
 * This Spring Boot application provides a production-ready full-stack e-commerce solution
 * with features including user authentication, product management, shopping cart, orders,
 * payments, and admin dashboard.
 * 
 * @author Senior Development Team
 * @version 1.0.0
 */
@SpringBootApplication
@EnableCaching
@EnableAsync
public class EcommercePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommercePlatformApplication.class, args);
    }
}
