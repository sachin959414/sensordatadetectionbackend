package com.wtp.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WtpDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(WtpDashboardApplication.class, args);
    }
}
