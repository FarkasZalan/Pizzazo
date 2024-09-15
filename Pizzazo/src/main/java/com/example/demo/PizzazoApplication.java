package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PizzazoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzazoApplication.class, args);
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
