package net.lesscoding.unified.core.model.vo.activemq.jolokia;

import lombok.Data;

/**
 * @author eleven
 * @date 2025/5/15 16:54
 * @apiNote
 */
@Data
public class SubscriberInfo {

    private Boolean Retroactive;
    private Integer CursorMemoryUsage;
    private Boolean Exclusive;
    private Boolean SlowConsumer;
    private Boolean DispatchAsync;
    private Boolean DestinationTemporary;
    private Integer MaximumPendingMessageLimit;
    private String ConnectionId;
    private Integer SubscriptionId;
    private Boolean Network;
    private String DestinationName;
    private String ClientId;
    private Boolean DestinationQueue;
    private Integer SessionId;
    private Integer EnqueueCounter;
    private Object Selector;
    private Boolean DestinationTopic;
    private Object UserName;
    private Boolean CursorFull;
    private Integer DispatchedQueueSize;
    private Integer Priority;
    private Integer PendingQueueSize;
    private Object Connection;
    private Integer MessageCountAwaitingAcknowledge;
    private String SubscriptionName;
    private Integer PrefetchSize;
    private Integer CursorPercentUsage;
    private Boolean Active;
    private Boolean NoLocal;
    private Boolean Durable;
    private Integer DequeueCounter;
    private Integer ConsumedCount;
    private Integer DispatchedCounter;
}
