package com.liushihao.application;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class KafkaMessageTest {

    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    public void setKafkaTemplate(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Test
    public void test(){
        for (int  i=0;i<10000;i++){
            kafkaTemplate.send("service-log-topic","这是第"+i+"条消息");
        }
    }
}
