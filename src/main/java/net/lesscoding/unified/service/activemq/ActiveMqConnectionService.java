package net.lesscoding.unified.service.activemq;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.ConsumerInfo;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.SearchResult;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.connection.ConnectionInfo;

import java.util.List;

/**
 * @author eleven
 * @date 2025/5/19 15:36
 * @apiNote
 */
public interface ActiveMqConnectionService {
    Page<ConnectionInfo> connectionList(CommonQueryDto<String> dto);

    Page<ConsumerInfo> connectionConsumers(CommonQueryDto<List<SearchResult>> dto);
}
