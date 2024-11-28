package com.sparta.currency_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CurrencyUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyUserApplication.class, args);
    }

}
