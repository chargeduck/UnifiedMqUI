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
     * BlockedProducerWarningInterval : 30000
     * ProducerCount : 0
     * MessageGroupType : cached
     * MaxMessageSize : 0
     * UseCache : true
     * SlowConsumerStrategy : null
     */

    private boolean ProducerFlowControl;
    private String Options;
    private boolean AlwaysRetroactive;
    private int MemoryUsageByteCount;
    private int AverageBlockedTime;
    private int MemoryPercentUsage;
    private int CursorMemoryUsage;
    private int InFlightCount;
    private boolean SendDuplicateFromStoreToDLQ;
    private boolean CacheEnabled;
    private int ForwardCount;
    private int StoreMessageSize;
    private boolean DLQ;
    private int AverageEnqueueTime;
    private String Name;
    private int MaxAuditDepth;
    private int TotalBlockedTime;
    private int BlockedSends;
    private int QueueSize;
    private int MaxPageSize;
    private long TempUsageLimit;
    private boolean PrioritizedMessages;
    private int MemoryUsagePortion;
    private boolean Paused;
    private int EnqueueCount;
    private int TempUsagePercentUsage;
    private int ConsumerCount;
    private int AverageMessageSize;
    private boolean CursorFull;
    private int MaxProducersToAudit;
    private int ExpiredCount;
    private int CursorPercentUsage;
    private int DuplicateFromStoreCount;
    private int MinEnqueueTime;
    private int MemoryLimit;
    private int MinMessageSize;
    private int DispatchCount;
    private int MaxEnqueueTime;
    private int DequeueCount;
    private int BlockedProducerWarningInterval;
    private int ProducerCount;
    private String MessageGroupType;
    private int MaxMessageSize;
    private boolean UseCache;
    private Object SlowConsumerStrategy;
    private List<?> Subscriptions;
}
