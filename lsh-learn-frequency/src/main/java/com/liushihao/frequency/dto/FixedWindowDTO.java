package com.liushihao.frequency.dto;

import lombok.Data;

/**
 * @author admin
 * 限流策略定义
 */
@Data
public class FixedWindowDTO extends FrequencyControlDTO {
    /**
     * 频控时间范围，默认单位秒
     *
     * @return 时间范围
     */
    private Integer time;
}
