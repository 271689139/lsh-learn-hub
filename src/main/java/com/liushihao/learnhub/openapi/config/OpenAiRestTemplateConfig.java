package com.liushihao.learnhub.openapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author admin
 */
@Component
public class OpenAiRestTemplateConfig {

    private OpenAiConfig openAiConfig;

    @Autowired
    public void setOpenAiConfig(OpenAiConfig openAiConfig) {
        this.openAiConfig = openAiConfig;
    }

    @Bean
    @Qualifier("openaiRestTemplate")
    public RestTemplate openAiRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + openAiConfig.getKey());
            return execution.execute(request, body);
        });
        return restTemplate;

    }
}
