package com.liushihao.learnhub;

import com.alibaba.fastjson.JSON;
import com.liushihao.learnhub.openapi.config.OpenAiConfig;
import com.liushihao.learnhub.openapi.dto.ChatRequest;
import com.liushihao.learnhub.openapi.dto.ChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles
public class OpenAiTest {

    OpenAiConfig openAiConfig;
    RestTemplate restTemplate;

    @Autowired
    public void setOpenAiConfig(OpenAiConfig openAiConfig) {
        this.openAiConfig = openAiConfig;
    }

    @Autowired
    @Qualifier("openaiRestTemplate")
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Test
    public void test() {
        String prompt = "你是谁";
        ChatRequest request = new ChatRequest(openAiConfig.getModel(), prompt);
        ChatResponse response = restTemplate.postForObject(openAiConfig.getUrl(), request, ChatResponse.class);
        System.out.println(JSON.toJSONString(response));
    }
}
