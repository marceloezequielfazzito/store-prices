package com.store.prices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.store.prices")
public class StorePricesApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorePricesApplication.class, args);
    }

}
