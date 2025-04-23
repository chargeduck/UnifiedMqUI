package net.lesscoding.unified.service.impl;

import net.lesscoding.unified.adapter.MqAdapter;
import net.lesscoding.unified.entity.ConnectConfig;
import net.lesscoding.unified.service.MqConnectManagerService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author eleven
 * @date 2025/4/23 15:34
 * @apiNote
 */
@Service
public class MqConnectManagerServiceImpl implements MqConnectManagerService {

    private final ConcurrentHashMap<String, MqAdapter> connections = new ConcurrentHashMap<>();

    public MqAdapter getConnection(ConnectConfig config) {
        String key = generateKey(config);
        return connections.computeIfAbsent(key, k -> createNewConnection(config));
    }

    private String generateKey(ConnectConfig config) {
        return String.format("%s-%s-%s", config.getMqType(),
                String.join(",", config.getHost()), config.getPort());
    }
}
