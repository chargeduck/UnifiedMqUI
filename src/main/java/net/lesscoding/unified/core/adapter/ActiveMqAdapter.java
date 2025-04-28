package net.lesscoding.unified.core.adapter;

import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ActiveMqAdapter implements MqAdapter {

    private final JolokiaUtil jolokiaUtil;

    private final ConnectConfigMapper connectConfigMapper;
    private final Gson gson;

    @Override
    public Connection getConnection(ConnectConfig connectConfig) throws JMSException {
        String connectionKey = MqAdapter.connectionKey(connectConfig);
        return CONNECTION_MAP.computeIfAbsent(connectionKey, key -> {
            String brokerUrl = StrUtil.format("tcp://{}:{}", connectConfig.getHost(), connectConfig.getPort());
            // 1. 创建连接工厂
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerUrl);
            // 2. 创建连接
            try {
                Connection connection = factory.createConnection();
                connection.start();
                return connection;
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        });
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
    public String brokerName(ConnectConfig connectConfig) throws JMSException {
        return ((ActiveMQConnection)getConnection(connectConfig)).getBrokerName();
    }

    @Override
    public ConnectConfig getMqInfo(ConnectConfig connectConfig) {
        try {
            String brokerName = brokerName(connectConfig);
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
