package net.lesscoding.unified.core.model.vo.activemq.jolokia;

import lombok.Data;

/**
 * @author eleven
 * @date 2025/5/21 15:01
 * @apiNote
 */
@Data
public class NetworkBridge {

    private String LocalBrokerName;
    private String RemoteAddress;
    private String LocalAddress;
    private String RemoteBrokerName;
    private Boolean CreatedByDuplex;
    private String RemoteBrokerId;
    private Integer ReceivedCounter;
    private Integer DequeueCounter;
    private Integer EnqueueCounter;
}
