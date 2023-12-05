package com.liushihao.application;

import com.liushihao.frequency.service.FrequencyServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LshLearnApplicationTests {

    FrequencyServiceImpl frequencyService;

    @Autowired
    public void setFrequencyService(FrequencyServiceImpl frequencyService) {
        this.frequencyService = frequencyService;
    }

    @Test
    void contextLoads() {
    }

}
