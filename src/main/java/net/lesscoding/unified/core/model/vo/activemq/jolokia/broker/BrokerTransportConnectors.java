package net.lesscoding.unified.core.model.vo.activemq.jolokia.broker;

import lombok.Data;

/**
 * @author eleven
 * @date 2025/4/28 14:39
 * @apiNote
 */
@Data
public class BrokerTransportConnectors {
    private String openwire;
    private String amqp;
    private String mqtt;
    private String stomp;
    private String ws;

}
