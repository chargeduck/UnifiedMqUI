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
    QUEUE_SM("org.apache.activemq:brokerName={},destinationName={},destinationType=Queue,type=Broker"),
    // 操作queue的 mbean format
    QUEUE_OP("org.apache.activemq:brokerName={},destinationName={},destinationType=Queue,type=Broker");


    private final String format;
}
