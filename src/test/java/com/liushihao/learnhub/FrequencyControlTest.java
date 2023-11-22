package com.liushihao.learnhub;

import com.liushihao.learnhub.dto.FixedWindowDTO;
import com.liushihao.learnhub.frequancycontrol.FrequencyControlUtil;
import com.liushihao.learnhub.utils.FrequencyControlConstant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FrequencyControlTest {

    @Test
    public void fixedWindowTest() {
        for (int i = 0; i <= 20; i++) {
            test();
        }
    }

    public void test() {
        // 固定时间窗口 5秒钟内 不允许调用超过10次
        FixedWindowDTO fixedWindowDTO = new FixedWindowDTO();
        fixedWindowDTO.setKey("zhangfei");
        fixedWindowDTO.setCount(10);
        fixedWindowDTO.setUnit(TimeUnit.MILLISECONDS);
        fixedWindowDTO.setTime(5000);
        try {
            FrequencyControlUtil.executeWithFrequencyControl(FrequencyControlConstant.TOTAL_COUNT_WITH_IN_FIX_TIME, fixedWindowDTO, this::methodNotify);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
    }

    public void methodNotify() {
        System.out.println("调用了");
    }
}
