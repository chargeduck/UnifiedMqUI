package net.lesscoding.unified.conn;

import net.lesscoding.unified.entity.ConnectConfig;
import net.lesscoding.unified.exception.MqException;
import org.springframework.stereotype.Component;

/**
 * @author eleven
 * @date 2025/4/23 15:55
 * @apiNote
 */
@Component
public class ActiveMqConnect implements MqConnect{
    @Override
    public void close() throws MqException {

    }

    @Override
    public boolean isClosed() throws MqException {
        return false;
    }

    @Override
    public ConnectConfig getMqConnectConfig() throws MqException {
        return null;
    }

    @Override
    public boolean sendMessage(Object message) throws MqException {
        return false;
    }
}
