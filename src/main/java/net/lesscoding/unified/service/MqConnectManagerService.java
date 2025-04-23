package net.lesscoding.unified.service;

import net.lesscoding.unified.entity.ConnectConfig;

/**
 * @author eleven
 * @date 2025/4/23 15:33
 * @apiNote
 */
public interface MqConnectManagerService {
    boolean createMqConnect(ConnectConfig connectConfig);
}
