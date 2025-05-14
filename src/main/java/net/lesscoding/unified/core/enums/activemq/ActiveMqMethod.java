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
    BROKER_QUEUES(""),
    BROKER_ADD_QUEUE("addQueue(java.lang.String)"),
    BROKER_REMOVE_QUEUE("removeQueue(java.lang.String)"),
    BROKER_INFO(""),
    QUEUE_REMOVE_MESSAGE("removeMessage(java.lang.String)"),
    QUEUE_BROWSE("browse()"),
    QUEUE_PAUSE("pause()"),
    QUEUE_RESUME("resume()"),
    QUEUE_PURGE("purge()"),
    ;

    private final String name;
}
