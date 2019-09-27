package com.sgic.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class DefectTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DefectTrackerApplication.class, args);
    }
}
