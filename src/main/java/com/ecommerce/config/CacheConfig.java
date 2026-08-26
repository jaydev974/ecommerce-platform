package com.ecommerce.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Caching configuration.
 * 
 * Implements in-memory caching for frequently accessed data.
 * 
 * @author Performance Team
 * @version 1.0.0
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(
                "products",
                "categories",
                "brands",
                "users",
                "orders",
                "coupons"
        );
    }
}

