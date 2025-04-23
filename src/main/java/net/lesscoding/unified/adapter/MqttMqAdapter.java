package net.lesscoding.unified.adapter;

import net.lesscoding.unified.entity.ConnectConfig;
import org.springframework.stereotype.Component;

/**
 * @author eleven
 * @date 2025/4/23 15:23
 * @apiNote
 */
@Component
public class MqttMqAdapter implements MqAdapter {
    @Override
    public boolean connect(ConnectConfig connectConfig) {
        return false;
    }

    @Override
    public boolean disconnect(ConnectConfig connectConfig) {
        return false;
    }

    @Override
    public boolean sendMessage(Object message) {
        return false;
    }
}
