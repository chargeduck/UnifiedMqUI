package net.lesscoding.unified.core.model.vo.activemq.jolokia.topic;
import lombok.Data;

import java.util.List;

/**
 * @author eleven
 * @date 2025/5/14 17:28
 * @apiNote
 */
@Data
public class TopicInfo {
    private Boolean ProducerFlowControl;
    private String Options;
    private Boolean AlwaysRetroactive;
    private Integer MemoryUsageByteCount;
    private Double AverageBlockedTime;
    private Integer MemoryPercentUsage;
    private Integer InFlightCount;
    private Boolean SendDuplicateFromStoreToDLQ;
    private Integer ForwardCount;
    private Boolean DLQ;
    private Integer StoreMessageSize;
    private Double AverageEnqueueTime;
    private String Name;
    private Integer TotalBlockedTime;
    private Integer BlockedSends;
    private Integer MaxAuditDepth;
    private Integer QueueSize;
    private Integer MaxPageSize;
    private Long TempUsageLimit;
    private Boolean PrioritizedMessages;
    private Integer MemoryUsagePortion;
    private Integer EnqueueCount;
    private Integer TempUsagePercentUsage;
    private Integer ConsumerCount;
    private Double AverageMessageSize;
    private Integer ExpiredCount;
    private Integer MaxProducersToAudit;
    private Integer MinEnqueueTime;
    private Integer DuplicateFromStoreCount;
    private Integer MemoryLimit;
    private Integer MinMessageSize;
    private Integer DispatchCount;
    private Integer MaxEnqueueTime;
    private Integer DequeueCount;
    private Integer BlockedProducerWarningInterval;
    private Integer ProducerCount;
    private Integer MaxMessageSize;
    private Boolean UseCache;
    private Object SlowConsumerStrategy;
    private List<?> Subscriptions;
}
