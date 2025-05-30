package net.lesscoding.unified.service.rocketmq;

import net.lesscoding.unified.core.model.vo.rocketmq.BrokerInfo;
import net.lesscoding.unified.entity.ConnectConfig;

import java.util.List;

/**
 * @author eleven
 * @date 2025/5/29 15:20
 * @apiNote
 */
public interface RocketMqBrokerService {
    List<BrokerInfo> getBrokerInfo(ConnectConfig connectConfig) throws Exception;
}
