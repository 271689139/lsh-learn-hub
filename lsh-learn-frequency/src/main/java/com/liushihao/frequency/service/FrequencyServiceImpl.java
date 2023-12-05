package com.liushihao.frequency.service;

import com.liushihao.frequency.annotaion.FrequencyControl;
import com.liushihao.frequency.utils.FrequencyControlConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author admin
 */
@Slf4j
@Service
public class FrequencyServiceImpl {

    @FrequencyControl(strategy = FrequencyControlConstant.SLIDING_WINDOW, count = 10, time = 10)
    public void frequencySlidingWindow() {
        log.info("调用滑动窗口窗口限流");
    }
}
