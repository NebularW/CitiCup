package com.flower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FlowerBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlowerBackendApplication.class, args);
    }

}
