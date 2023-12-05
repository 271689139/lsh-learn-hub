package com.liushihao.frequency.aspect;

import cn.hutool.core.util.StrUtil;
import com.liushihao.frequency.annotaion.FrequencyControl;
import com.liushihao.frequency.dto.FixedWindowDTO;
import com.liushihao.frequency.dto.SlidingWindowDTO;
import com.liushihao.frequency.dto.TokenBucketDTO;
import com.liushihao.frequency.frequancycontrol.FrequencyControlUtil;
import com.liushihao.frequency.utils.FrequencyControlConstant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author admin
 * 切面- 频控实现
 */
@Slf4j
@Aspect
@Component
public class FrequencyControlAspect {

    @Around("@annotation(com.liushihao.frequency.annotaion.FrequencyControl)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        FrequencyControl frequencyControl = method.getAnnotation(FrequencyControl.class);
        // 获取限流的策略
        String strategy = frequencyControl.strategy();
        String key = StrUtil.isBlank(frequencyControl.key()) ? method.toGenericString() : frequencyControl.key();
        if (FrequencyControlConstant.SLIDING_WINDOW.equals(strategy)) {
            // 滑动窗口
            SlidingWindowDTO slidingWindowDTO = buildSlidingWindowFrequencyControlDTO(key, frequencyControl);
            return FrequencyControlUtil.executeWithFrequencyControl(strategy, slidingWindowDTO, () -> joinPoint.proceed());
        } else if (FrequencyControlConstant.TOKEN_BUCKET.equals(strategy)) {
            // 令牌筒
            TokenBucketDTO tokenBucketDTO = buildTokenBucketDTO(key, frequencyControl);
            return FrequencyControlUtil.executeWithFrequencyControl(strategy, tokenBucketDTO, () -> joinPoint.proceed());
        } else {
            // 固定时间窗口
            FixedWindowDTO fixedWindowDTO = buildFixedWindowDTO(key, frequencyControl);
            return FrequencyControlUtil.executeWithFrequencyControl(strategy, fixedWindowDTO, () -> joinPoint.proceed());
        }
    }

    /**
     * 将注解参数转换为编程式调用所需要的参数
     *
     * @param key              频率控制Key
     * @param frequencyControl 注解
     * @return 编程式调用所需要的参数-FrequencyControlDTO
     */
    private SlidingWindowDTO buildSlidingWindowFrequencyControlDTO(String key, FrequencyControl frequencyControl) {
        SlidingWindowDTO frequencyControlDTO = new SlidingWindowDTO();
        frequencyControlDTO.setWindowSize(frequencyControl.windowSize());
        frequencyControlDTO.setPeriod(frequencyControl.period());
        frequencyControlDTO.setCount(frequencyControl.count());
        frequencyControlDTO.setUnit(frequencyControl.unit());
        frequencyControlDTO.setKey(key);
        return frequencyControlDTO;
    }

    /**
     * 将注解参数转换为编程式调用所需要的参数
     *
     * @param key              频率控制Key
     * @param frequencyControl 注解
     * @return 编程式调用所需要的参数-FrequencyControlDTO
     */
    private TokenBucketDTO buildTokenBucketDTO(String key, FrequencyControl frequencyControl) {
        TokenBucketDTO tokenBucketDTO = new TokenBucketDTO(frequencyControl.capacity(), frequencyControl.refillRate());
        tokenBucketDTO.setKey(key);
        return tokenBucketDTO;
    }

    /**
     * 将注解参数转换为编程式调用所需要的参数
     *
     * @param key              频率控制Key
     * @param frequencyControl 注解
     * @return 编程式调用所需要的参数-FrequencyControlDTO
     */
    private FixedWindowDTO buildFixedWindowDTO(String key, FrequencyControl frequencyControl) {
        FixedWindowDTO fixedWindowDTO = new FixedWindowDTO();
        fixedWindowDTO.setCount(frequencyControl.count());
        fixedWindowDTO.setTime(frequencyControl.time());
        fixedWindowDTO.setUnit(frequencyControl.unit());
        fixedWindowDTO.setKey(key);
        return fixedWindowDTO;
    }


}
