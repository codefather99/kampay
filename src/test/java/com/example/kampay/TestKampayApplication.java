package com.example.kampay;

import org.springframework.boot.SpringApplication;

public class TestKampayApplication {

    public static void main(String[] args) {
        SpringApplication.from(KampayApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
