package net.lesscoding.unified.service.activemq;

import net.lesscoding.unified.core.model.dto.activemq.QueueQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueInfo;

import java.util.List;

/**
 * @author eleven
 * @date 2025/5/13 14:14
 * @apiNote
 */
public interface ActiveMqBrokerService {
    List<QueueInfo> queueList(QueueQueryDto dto);

    Boolean removeQueue(QueueQueryDto dto);

    Boolean addQueue(QueueQueryDto dto);
}
