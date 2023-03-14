package com.example.carmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CarManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarManagementApplication.class, args);
    }

}
