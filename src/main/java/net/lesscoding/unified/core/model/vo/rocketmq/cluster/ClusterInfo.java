package net.lesscoding.unified.core.model.vo.rocketmq.cluster;

import lombok.Data;

/**
 * @author eleven
 * @date 2025/5/29 16:19
 * @apiNote
 */
@Data
public class ClusterInfo {

    private BrokerAddrTable brokerAddrTable;

    private clusterAddrTable clusterAddrTable;

}
