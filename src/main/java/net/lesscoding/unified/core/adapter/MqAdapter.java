package net.lesscoding.unified.core.adapter;

import cn.hutool.core.util.StrUtil;
import net.lesscoding.unified.entity.ConnectConfig;

import javax.jms.Connection;
import javax.jms.JMSException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author eleven
 * @date 2025/4/23 15:20
 * @apiNote
 */
public interface MqAdapter {

    public static final ConcurrentHashMap<String, Connection> CONNECTION_MAP = new ConcurrentHashMap<>();

    public static String connectionKey(ConnectConfig connectConfig) {
        return StrUtil.format("{}:{}", connectConfig.getHost(), connectConfig.getPort());
    }
    public Connection getConnection(ConnectConfig connectConfig) throws JMSException;

    public boolean disconnect(ConnectConfig connectConfig);

    public boolean sendMessage(Object message);

    public String brokerName(Connection connection) throws JMSException;

    public ConnectConfig getMqInfo(ConnectConfig connectConfig);
}
