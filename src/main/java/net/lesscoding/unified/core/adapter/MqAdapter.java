package net.lesscoding.unified.core.adapter;

import net.lesscoding.unified.entity.ConnectConfig;

/**
 * @author eleven
 * @date 2025/4/23 15:20
 * @apiNote
 */
public interface MqAdapter {

    public boolean connect(ConnectConfig connectConfig);

    public boolean disconnect(ConnectConfig connectConfig);

    public boolean sendMessage(Object message);
}
