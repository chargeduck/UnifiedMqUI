package net.lesscoding.unified.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lesscoding.unified.core.adapter.MqAdapter;
import net.lesscoding.unified.core.adapter.MqAdapterFactory;
import net.lesscoding.unified.entity.ConnectConfig;
import net.lesscoding.unified.mapper.ConnectConfigMapper;
import net.lesscoding.unified.service.MqConnectManagerService;
import org.springframework.stereotype.Service;

import javax.jms.Connection;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author eleven
 * @date 2025/4/23 15:34
 * @apiNote
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MqConnectManagerServiceImpl implements MqConnectManagerService {
    private final MqAdapterFactory mqAdapterFactory;

    private final ConnectConfigMapper connectConfigMapper;

    @Override
    public ConnectConfig createMqConnect(ConnectConfig connectConfig) {
        if(StrUtil.isBlank(connectConfig.getTitle())) {
            connectConfig.setTitle(MqAdapter.connectionKey(connectConfig));
        }
        MqAdapter adapter = mqAdapterFactory.getMqAdapter(connectConfig.getMqTypeEnum());
        connectConfig = adapter.getMqInfo(connectConfig);
        ConnectConfig dataRows = connectConfigMapper.selectOne(new LambdaQueryWrapper<ConnectConfig>()
                .eq(ConnectConfig::getMqType, connectConfig.getMqType())
                .eq(ConnectConfig::getHost, connectConfig.getHost())
                .eq(ConnectConfig::getPort, connectConfig.getPort())
        );
        if (dataRows == null) {
            connectConfig.setCreateTime(LocalDateTime.now());
            connectConfigMapper.insertConnectConfig(connectConfig);
            log.info("不存在对应MQ数据，新增配置项");
        } else {
            connectConfig.setId(dataRows.getId());
            connectConfig.setCreateTime(dataRows.getCreateTime());
            connectConfigMapper.updateById(connectConfig);
            log.info("已存在对应MQ数据，编辑配置项");
        }
        return connectConfig;
    }

    @Override
    public Page<List<ConnectConfig>> mqList(ConnectConfig connectConfig) {
        PageDTO page = connectConfig.getPage();
        List<ConnectConfig> connectConfigs = connectConfigMapper.getMqList(page, connectConfig);
        List<List<ConnectConfig>> partition = Lists.partition(connectConfigs, 3);
        page.setRecords(partition);
        return page;
    }

    @Override
    public ConnectConfig getMqConnect(Integer id) {
        ConnectConfig config = connectConfigMapper.selectById(id);
        if (config != null) {
            try {
                MqAdapter adapter = mqAdapterFactory.getMqAdapter(config.getMqTypeEnum());
                Connection connection = adapter.getConnection(config);
                if (connection != null) {
                    config = adapter.getMqInfo(config);
                }
            } catch (Exception e) {
                log.error("获取MQ连接失败", e);
            }
            connectConfigMapper.updateById(config);
        }
        return config;
    }

    @Override
    public Integer delById(Integer id) {
        return connectConfigMapper.deleteById(id);
    }
}
