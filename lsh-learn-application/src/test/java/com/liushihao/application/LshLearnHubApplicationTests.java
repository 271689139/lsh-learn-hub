package com.liushihao.application;

import com.liushihao.frequency.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LshLearnHubApplicationTests {

    @Test
    void contextLoads() {
        RedisUtils.set("123key", "张飞");
        System.out.println(RedisUtils.getStr("123key"));
    }

}
