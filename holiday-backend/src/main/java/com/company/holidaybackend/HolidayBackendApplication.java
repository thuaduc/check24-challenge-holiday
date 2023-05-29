package com.company.holidaybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HolidayBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HolidayBackendApplication.class, args);
    }
}
