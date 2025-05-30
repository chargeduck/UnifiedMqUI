package net.lesscoding.unified.core.adapter;

import cn.hutool.core.collection.CollUtil;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lesscoding.unified.core.model.vo.rocketmq.BrokerInfo;
import net.lesscoding.unified.entity.ConnectConfig;
import net.lesscoding.unified.utils.RocketMqUtil;
import org.springframework.stereotype.Component;

import javax.jms.Connection;
import java.util.List;

/**
 * @author eleven
 * @date 2025/4/23 15:22
 * @apiNote
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class RocketMqAdapter implements MqAdapter {

    private final RocketMqUtil mqUtil;

    private final Gson gson;

    @Override
    public Connection getConnection(ConnectConfig connectConfig) {
        String connectionKey = MqAdapter.connectionKey(connectConfig);
        boolean containsed = CONNECTION_MAP.containsKey(connectionKey);
        if (containsed) {
            return CONNECTION_MAP.get(connectionKey);
        } else {
            return DefaultConnect.instance();
        }
    }

    @Override
    public boolean disconnect(ConnectConfig connectConfig) {
        return false;
    }

    @Override
    public boolean sendMessage(Object message) {
        return false;
    }

    @Override
    public String brokerName(Connection connection) {
        return null;
    }

    @Override
    public ConnectConfig getMqInfo(ConnectConfig connectConfig) {
        try {
            List<BrokerInfo> brokerInfos = mqUtil.brokerServerList(connectConfig);
            if (CollUtil.isNotEmpty(brokerInfos)) {
                BrokerInfo brokerInfo = brokerInfos.get(0);
                connectConfig.setBrokerName(brokerInfo.getBrokerName());
                connectConfig.setBrokerId(brokerInfo.getBrokerId());
                connectConfig.setActiveFlag(true);
                connectConfig.setVersion(brokerInfo.getBrokerVersionDesc());
                connectConfig.setExtra(gson.toJson(brokerInfos));
            } else {
                connectConfig.setActiveFlag(false);
            }
        } catch (Exception e) {
            log.info("获取Broker信息失败");
        }
        return connectConfig;
    }
}
