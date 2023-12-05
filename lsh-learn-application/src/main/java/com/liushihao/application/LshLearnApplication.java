package com.liushihao.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.liushihao.*"})
public class LshLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(LshLearnApplication.class, args);
    }

}
