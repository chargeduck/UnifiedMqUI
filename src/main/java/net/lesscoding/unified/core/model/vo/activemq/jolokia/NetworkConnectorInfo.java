package net.lesscoding.unified.core.model.vo.activemq.jolokia;

import lombok.Data;

/**
 * @author eleven
 * @date 2025/5/21 13:55
 * @apiNote
 */
@Data
public class NetworkConnectorInfo {

    private String UserName;
    private Integer ConsumerTTL;
    private Boolean DispatchAsync;
    private Integer PrefetchSize;
    private Integer MessageTTL;
    private Boolean Duplex;
    private String Name;
    private Integer AdvisoryPrefetchSize;
    private String RemotePassword;
    private Boolean DynamicOnly;
    private Boolean BridgeTempDestinations;
    private Boolean DecreaseNetworkConsumerPriority;
    private Boolean ConduitSubscriptions;
    private String RemoteUserName;
    private Boolean SuppressDuplicateTopicSubscriptions;
    private Boolean SuppressDuplicateQueueSubscriptions;
    private String Password;
}
