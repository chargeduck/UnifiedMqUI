package net.lesscoding.unified.core.adapter;

import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.ActiveMqJolokiaResponse;
import net.lesscoding.unified.entity.ConnectConfig;
import net.lesscoding.unified.mapper.ConnectConfigMapper;
import net.lesscoding.unified.utils.activemq.JolokiaUtil;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

import javax.jms.Connection;
import javax.jms.JMSException;

/**
 * @author eleven
 * @date 2025/4/23 15:22
 * @apiNote
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class ActiveMqAdapter implements MqAdapter {

    private final JolokiaUtil jolokiaUtil;

    private final ConnectConfigMapper connectConfigMapper;
    private final Gson gson;

    @Override
    public Connection getConnection(ConnectConfig connectConfig) {
        String connectionKey = MqAdapter.connectionKey(connectConfig);
        boolean containsed = CONNECTION_MAP.containsKey(connectionKey);
        if (containsed) {
            return CONNECTION_MAP.get(connectionKey);
        } else {
            String brokerUrl = StrUtil.format("tcp://{}:{}", connectConfig.getHost(), connectConfig.getPort());
            // 1. 创建连接工厂
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerUrl);
            // 2. 创建连接
            Connection connection = null;
            try {
                connection = factory.createConnection();
                connection.start();
                connectConfig.setActiveFlag(true);
                CONNECTION_MAP.put(connectionKey, connection);
            } catch (JMSException e) {
                log.error("创建连接失败", e);
                connectConfig.setActiveFlag(false);
            }
            return connection;
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
    public String brokerName(Connection connection) throws JMSException {
        return ((ActiveMQConnection)connection).getBrokerName();
    }

    @Override
    public ConnectConfig getMqInfo(ConnectConfig connectConfig) {
        Connection connection = getConnection(connectConfig);
        if (connection == null) {
            return connectConfig;
        }
        try {
            String brokerName = brokerName(connection);
            connectConfig.setBrokerName(brokerName);
        } catch (JMSException e) {
            Object brokerList = jolokiaUtil.getBrokerList(connectConfig);
            System.out.println("brokerList = " + brokerList);
            throw new RuntimeException(e);
        }
        String brokerJson = jolokiaUtil.getBrokerInfo(connectConfig);
        ActiveMqJolokiaResponse brokerInfo = gson.fromJson(brokerJson, ActiveMqJolokiaResponse.class);
        connectConfig.setBrokerId(brokerInfo.getValue().getBrokerId());
        connectConfig.setUpTime(brokerInfo.getValue().getUptime());
        connectConfig.setVersion(brokerInfo.getValue().getBrokerVersion());
        connectConfig.setExtra(brokerJson);
        return connectConfig;
    }
}
