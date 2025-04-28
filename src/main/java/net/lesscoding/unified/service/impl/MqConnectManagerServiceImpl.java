package net.lesscoding.unified.service.impl;

import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.core.adapter.MqAdapter;
import net.lesscoding.unified.core.adapter.MqAdapterFactory;
import net.lesscoding.unified.entity.ConnectConfig;
import net.lesscoding.unified.mapper.ConnectConfigMapper;
import net.lesscoding.unified.service.MqConnectManagerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author eleven
 * @date 2025/4/23 15:34
 * @apiNote
 */
@Service
@RequiredArgsConstructor
public class MqConnectManagerServiceImpl implements MqConnectManagerService {

    private final MqAdapterFactory mqAdapterFactory;

    private final ConnectConfigMapper connectConfigMapper;

    private final ConcurrentHashMap<String, MqAdapter> connections = new ConcurrentHashMap<>();

    @Override
    public ConnectConfig createMqConnect(ConnectConfig connectConfig) {
        return mqAdapterFactory.getMqAdapter(connectConfig.getMqType())
                .getMqInfo(connectConfig);
    }

    @Override
    public List<ConnectConfig> mqList() {
        return connectConfigMapper.selectList(null);
    }
}
