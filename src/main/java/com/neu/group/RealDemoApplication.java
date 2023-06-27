package com.neu.group;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RealDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealDemoApplication.class, args);
    }

}
