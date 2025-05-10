package net.lesscoding.unified.core.model.vo.activemq.jolokia.queue;

import lombok.Data;
import java.util.List;

/**
 * @author eleven
 * @date 2025/5/9 15:11
 * @apiNote
 */
@Data
public class QueueInfo {
    /**
     * ProducerFlowControl : true
     * Options :
     * AlwaysRetroactive : false
     * MemoryUsageByteCount : 0
     * AverageBlockedTime : 0
     * MemoryPercentUsage : 0
     * CursorMemoryUsage : 0
     * InFlightCount : 0
     * Subscriptions : []
     * SendDuplicateFromStoreToDLQ : true
     * CacheEnabled : true
     * ForwardCount : 0
     * StoreMessageSize : 0
     * DLQ : false
     * AverageEnqueueTime : 0
     * Name : 1234
     * MaxAuditDepth : 2048
     * TotalBlockedTime : 0
     * BlockedSends : 0
     * QueueSize : 0
     * MaxPageSize : 200
     * TempUsageLimit : 53687091200
     * PrioritizedMessages : false
     * MemoryUsagePortion : 1
     * Paused : false
     * EnqueueCount : 0
     * MessageGroups : {}
     * TempUsagePercentUsage : 0
     * ConsumerCount : 0
     * AverageMessageSize : 0
     * CursorFull : false
     * MaxProducersToAudit : 1024
     * ExpiredCount : 0
     * CursorPercentUsage : 0
     * DuplicateFromStoreCount : 0
     * MinEnqueueTime : 0
     * MemoryLimit : 751619277
     * MinMessageSize : 0
     * DispatchCount : 0
     * MaxEnqueueTime : 0
     * DequeueCount : 0
     * BlockedProducerWarningIntegererval : 30000
     * ProducerCount : 0
     * MessageGroupType : cached
     * MaxMessageSize : 0
     * UseCache : true
     * SlowConsumerStrategy : null
     */

    private Boolean ProducerFlowControl;
    private String Options;
    private Boolean AlwaysRetroactive;
    private Integer MemoryUsageByteCount;
    private Integer AverageBlockedTime;
    private Integer MemoryPercentUsage;
    private Integer CursorMemoryUsage;
    private Integer InFlightCount;
    private Boolean SendDuplicateFromStoreToDLQ;
    private Boolean CacheEnabled;
    private Integer ForwardCount;
    private Integer StoreMessageSize;
    private Boolean DLQ;
    private Double AverageEnqueueTime;
    private String Name;
    private Integer MaxAuditDepth;
    private Integer TotalBlockedTime;
    private Integer BlockedSends;
    private Integer QueueSize;
    private Integer MaxPageSize;
    private long TempUsageLimit;
    private Boolean PrioritizedMessages;
    private Integer MemoryUsagePortion;
    private Boolean Paused;
    private Integer EnqueueCount;
    private Integer TempUsagePercentUsage;
    private Integer ConsumerCount;
    private Integer AverageMessageSize;
    private Boolean CursorFull;
    private Integer MaxProducersToAudit;
    private Integer ExpiredCount;
    private Integer CursorPercentUsage;
    private Integer DuplicateFromStoreCount;
    private Integer MinEnqueueTime;
    private Integer MemoryLimit;
    private Integer MinMessageSize;
    private Integer DispatchCount;
    private Integer MaxEnqueueTime;
    private Integer DequeueCount;
    private Integer BlockedProducerWarningIntegererval;
    private Integer ProducerCount;
    private String MessageGroupType;
    private Integer MaxMessageSize;
    private Boolean UseCache;
    private Object SlowConsumerStrategy;
    private List<?> Subscriptions;
}
