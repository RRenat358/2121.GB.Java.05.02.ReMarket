package ru.rrenat358.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("secret.properties")
public class ReMarketApp08 {

    public static void main(String[] args) {

        SpringApplication.run(ReMarketApp08.class, args);

    }


}
