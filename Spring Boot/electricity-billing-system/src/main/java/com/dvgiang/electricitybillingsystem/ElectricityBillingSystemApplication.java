package com.dvgiang.electricitybillingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class ElectricityBillingSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElectricityBillingSystemApplication.class, args);
    }
}
