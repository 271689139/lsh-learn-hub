package com.liushihao.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author admin
 * 消费服务日志
 */
@Component
@Slf4j
public class ServerLogKafkaListener {


    @KafkaListener(groupId = "${kafka.log.groupId}", topics = "${kafka.log.topic}", concurrency = "1", containerFactory = "batchFactory", clientIdPrefix = "server-log")
    public void onMessage(List<ConsumerRecord<String, String>> list) {
        for (ConsumerRecord<String, String> consumerRecord : list) {
            log.info("consumerRecord,主题:{},分区:{},value:{}", consumerRecord.topic(), consumerRecord.partition(), consumerRecord.value());
        }

    }
}
