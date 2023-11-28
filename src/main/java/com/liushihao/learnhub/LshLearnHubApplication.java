package com.liushihao.learnhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author admin
 */
@SpringBootApplication
@EnableConfigurationProperties
public class LshLearnHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(LshLearnHubApplication.class, args);
    }

}
