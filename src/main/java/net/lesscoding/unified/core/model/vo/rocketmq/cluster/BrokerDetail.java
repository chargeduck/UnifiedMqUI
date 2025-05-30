package net.lesscoding.unified.core.model.vo.rocketmq.cluster;

import lombok.Data;

import java.util.Map;

/**
 * @author eleven
 * @date 2025/5/29 16:20
 * @apiNote
 */
@Data
public class BrokerDetail {
    private String cluster;

    private String brokerName;

    private Map<String, String> brokerAddrs;
}
