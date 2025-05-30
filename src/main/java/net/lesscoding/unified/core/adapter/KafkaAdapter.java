package net.lesscoding.unified.core.adapter;

import net.lesscoding.unified.entity.ConnectConfig;
import org.springframework.stereotype.Component;

import javax.jms.Connection;

/**
 * @author eleven
 * @date 2025/4/23 15:23
 * @apiNote
 */
@Component
public class KafkaAdapter implements MqAdapter {
    @Override
    public Connection getConnection(ConnectConfig connectConfig) {
        return null;
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
        return null;
    }
}
