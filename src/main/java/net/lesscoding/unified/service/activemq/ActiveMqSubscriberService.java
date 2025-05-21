package net.lesscoding.unified.service.activemq;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.core.model.dto.activemq.SubscriberQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.ActiveMqJolokiaResponse;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.SearchResult;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.SubscriberInfo;
import net.lesscoding.unified.entity.ConnectConfig;

import java.util.List;

/**
 * @author eleven
 * @date 2025/5/16 13:51
 * @apiNote
 */
public interface ActiveMqSubscriberService {

    ActiveMqJolokiaResponse<List<SearchResult>> brokerSubscriptions(ConnectConfig config, String destinationName);

    List<SubscriberInfo> subscriberInfos(ConnectConfig config, SubscriberQueryDto dto);
    Page<SubscriberInfo> subscriberInfoPage(ConnectConfig config, SubscriberQueryDto dto, Page<?> page);

    Page<SubscriberInfo> subscriberInfoPage(CommonQueryDto<SubscriberQueryDto> dto);

    Page<SubscriberInfo> subscriberConditionPage(CommonQueryDto<SubscriberQueryDto> dto);
}
