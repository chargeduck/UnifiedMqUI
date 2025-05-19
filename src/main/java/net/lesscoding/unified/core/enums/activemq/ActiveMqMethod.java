package net.lesscoding.unified.core.enums.activemq;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author eleven
 * @date 2025/5/14 11:09
 * @apiNote
 */
@Getter
@AllArgsConstructor
public enum ActiveMqMethod {
    NONE(""),
    BROKER_INFO(""),
    BROKER_ADD_QUEUE("addQueue(java.lang.String)"),
    BROKER_ADD_TOPIC("addTopic(java.lang.String)"),
    BROKER_ADD_DURABLE_SUBSCRIBER("createDurableSubscriber(java.lang.String,java.lang.String,java.lang.String,java.lang.String)"),
    BROKER_REMOVE_QUEUE("removeQueue(java.lang.String)"),
    BROKER_REMOVE_TOPIC("removeTopic(java.lang.String)"),
    QUEUE_REMOVE_MESSAGE("removeMessage(java.lang.String)"),
    QUEUE_BROWSE("browse()"),
    QUEUE_PAUSE("pause()"),
    QUEUE_RESUME("resume()"),
    QUEUE_PURGE("purge()"),
    QUEUE_SEND_TEXT_MESSAGE_WITH_PROPERTIES("sendTextMessageWithProperties(java.lang.String)"),
    //
    TOPIC_SEND_TEXT_MESSAGE_WITH_PROPERTIES("sendTextMessageWithProperties(java.lang.String)"),
    ;

    private final String name;
}
