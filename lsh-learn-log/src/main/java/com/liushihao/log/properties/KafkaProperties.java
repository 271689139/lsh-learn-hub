package com.liushihao.log.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 日志发送kafka配置
 *
 * @author admin
 */
@Data
@ConfigurationProperties(prefix = "log.kafka")
@Configuration
public class KafkaProperties {

    /**
     * 项目名称
     */
    private String appName;
    /**
     * kafka server
     */
    private String server;
    /**
     * topic
     */
    private String topic;

    /**
     * 是否启用
     */
    private Boolean enable;


}
