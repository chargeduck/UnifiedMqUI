package net.lesscoding.unified.service;

import net.lesscoding.unified.core.model.dto.activemq.ActiveMqJolokiaQueueQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.ActiveMqJolokiaResponse;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueInfo;

import java.util.Map;

/**
 * @author eleven
 * @date 2025/5/9 15:42
 * @apiNote
 */
public interface ActiveMqService {
    ActiveMqJolokiaResponse<Map<String, QueueInfo>> queueList(ActiveMqJolokiaQueueQueryDto dto);
}
