package com.liushihao.frequency.annotaion;

import com.liushihao.frequency.utils.FrequencyControlConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 频控注解
 *
 * @author admin
 */
@Retention(RetentionPolicy.RUNTIME)// 运行时生效
@Target(ElementType.METHOD)//作用在方法上
public @interface FrequencyControl {
    /**
     * 策略
     */
    String strategy() default FrequencyControlConstant.TOTAL_COUNT_WITH_IN_FIX_TIME;

    /**
     * 窗口大小，默认 5 个 period
     */
    int windowSize() default 5;

    /**
     * 窗口最小周期 1s (窗口大小是 5s， 1s一个小格子，共10个格子)
     */
    int period() default 1;

    /**
     * 频控时间范围，默认单位秒
     *
     * @return 时间范围
     */
    int time();

    /**
     * 频控时间单位，默认秒
     *
     * @return 单位
     */
    TimeUnit unit() default TimeUnit.SECONDS;

    /**
     * 单位时间内最大访问次数
     *
     * @return 次数
     */
    int count();


    /**
     * key的前缀，默认取方法全限定名，除非我们在不同方法上对同一个资源做频控，就自己指定
     *
     * @return key的前缀
     */
    String key() default "";

    /**
     * @return 令牌桶容量
     */
    long capacity() default 3;

    /**
     * @return 每秒补充的令牌数
     */
    double refillRate() default 0.5;


}
