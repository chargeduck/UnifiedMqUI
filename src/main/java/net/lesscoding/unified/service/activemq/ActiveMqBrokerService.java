package net.lesscoding.unified.service.activemq;

import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueInfo;

import java.util.List;

/**
 * @author eleven
 * @date 2025/5/13 14:14
 * @apiNote
 */
public interface ActiveMqBrokerService {
    List<QueueInfo> queueList(CommonQueryDto<String> dto);

    Boolean removeQueue(CommonQueryDto<String> dto);

    Boolean addQueue(CommonQueryDto<String> dto);
}
