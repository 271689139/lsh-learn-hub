package com.liushihao.log.config;

import com.google.common.collect.Maps;
import com.liushihao.log.properties.KafkaProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

/**
 * @author admin
 * kafka生产者自动配置
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "log.kafka", name = "enable", havingValue = "true")
@EnableConfigurationProperties({KafkaProperties.class})
public class KafkaAutoConfiguration {

    private KafkaProperties kafkaProperties;

    @Autowired
    public void setKafkaProperties(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }


    private Map<String, Object> kafkaProperties() {
        Map<String, Object> properties = Maps.newHashMap();
        // 集群地址
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getServer());
        // 消息序列化方式
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //提交延时
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 0);
        //重试，0为不启用重试机制
        properties.put(ProducerConfig.RETRIES_CONFIG, 0);
        return properties;
    }


    @Bean("producerFactory")
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaProperties());
    }

    @Bean
    public KafkaTemplate<String, String> initKafkaTemplate(@Qualifier("producerFactory") ProducerFactory<String, String> producerFactory) {
        log.info("**********************init kafka-producer**********************");
        return new KafkaTemplate<>(producerFactory);
    }
}
