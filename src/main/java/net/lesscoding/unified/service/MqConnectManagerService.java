package net.lesscoding.unified.service;

import net.lesscoding.unified.entity.ConnectConfig;

import java.util.List;

/**
 * @author eleven
 * @date 2025/4/23 15:33
 * @apiNote
 */
public interface MqConnectManagerService {
    boolean createMqConnect(ConnectConfig connectConfig);

    List<ConnectConfig> mqList();
}
