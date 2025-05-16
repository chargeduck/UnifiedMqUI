package net.lesscoding.unified.core.enums.activemq;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author eleven
 * @date 2025/5/14 11:08
 * @apiNote
 */
@Getter
@AllArgsConstructor
public enum MbeanFormat {
    // 操作broker的 mbean format
    BROKER_OP("org.apache.activemq:type=Broker,brokerName={}"),
    // 操作topic的 mbean format
    TOPIC_OP("org.apache.activemq:brokerName={},type=Broker,destinationName={},destinationType=Topic"),
    // 操作queue的 mbean format
    QUEUE_OP("org.apache.activemq:brokerName={},type=Broker,destinationName={},destinationType=Queue"),
    SUBSCRIBER_OP("org.apache.activemq:type=Broker,brokerName={},destinationType=Topic,destinationName=*,endpoint=Consumer,clientId=*,consumerId=*"),
    ;


    private final String format;
}
