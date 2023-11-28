package com.liushihao.learnhub.openapi.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 */
@Configuration
@Data
public class OpenAiConfig {
    @Value("${openapi.key:}")
    private String key;

    @Value("${openapi.model}")
    private String model;

    @Value("${openapi.url}")
    private String url;
}
