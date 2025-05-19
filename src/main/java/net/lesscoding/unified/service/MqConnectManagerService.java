package net.lesscoding.unified.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.entity.ConnectConfig;

import java.util.List;

/**
 * @author eleven
 * @date 2025/4/23 15:33
 * @apiNote
 */
public interface MqConnectManagerService{
    ConnectConfig createMqConnect(ConnectConfig connectConfig);

    Page<List<ConnectConfig>> mqList(CommonQueryDto<ConnectConfig> dto);

    ConnectConfig getMqConnect(Integer id);

    Integer delById(Integer id);
}
