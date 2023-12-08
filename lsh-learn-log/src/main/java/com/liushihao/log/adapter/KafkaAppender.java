package com.liushihao.log.adapter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

/**
 * @author admin
 * 收集日志 发送给kafka
 */
public class KafkaAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
    @Override
    protected void append(ILoggingEvent iLoggingEvent) {

    }
}
