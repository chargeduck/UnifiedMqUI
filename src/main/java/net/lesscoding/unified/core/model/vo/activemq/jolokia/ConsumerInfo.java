package net.lesscoding.unified.core.model.vo.activemq.jolokia;

import lombok.Data;

/**
 * @author eleven
 * @date 2025/5/20 10:49
 * @apiNote
 */
@Data
public class ConsumerInfo {

    private Boolean Retroactive;
    private Boolean Exclusive;
    private Boolean SlowConsumer;
    private Boolean DispatchAsync;
    private Boolean DestinationTemporary;
    private String ConnectionId;
    private Integer MaximumPendingMessageLimit;
    private Integer SubscriptionId;
    private Boolean Network;
    private String DestinationName;
    private String ClientId;
    private Boolean DestinationQueue;
    private Integer SessionId;
    private Integer EnqueueCounter;
    private String Selector;
    private Boolean DestinationTopic;
    private String UserName;
    private Integer Priority;
    private Integer DispatchedQueueSize;
    private SearchResult Connection;
    private Integer PendingQueueSize;
    private Integer MessageCountAwaitingAcknowledge;
    private Integer PrefetchSize;
    private String SubscriptionName;
    private Integer MaximumPendingQueueSize;
    private Boolean Active;
    private Integer DiscardedCount;
    private Boolean NoLocal;
    private Boolean Durable;
    private Integer DequeueCounter;
    private Integer ConsumedCount;
    private Integer DispatchedCounter;


}
