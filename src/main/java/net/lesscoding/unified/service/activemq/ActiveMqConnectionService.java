package net.lesscoding.unified.service.activemq;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.*;

import java.util.List;

/**
 * @author eleven
 * @date 2025/5/19 15:36
 * @apiNote
 */
public interface ActiveMqConnectionService {
    Page<ConnectionInfo> connectionList(CommonQueryDto<String> dto);

    Page<ConsumerInfo> connectionConsumers(CommonQueryDto<List<SearchResult>> dto);

    List<NetworkConnectorInfo> networkConnectorList(CommonQueryDto<String> dto);

    Page<NetworkBridge> networkBridges(CommonQueryDto<String> dto);
}
