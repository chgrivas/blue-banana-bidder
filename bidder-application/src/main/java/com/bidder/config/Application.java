package com.bidder.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = {"com.bidder"})
@ImportResource("application-context.xml")
public class Application
{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
