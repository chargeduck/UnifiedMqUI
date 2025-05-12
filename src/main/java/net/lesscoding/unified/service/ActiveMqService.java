package net.lesscoding.unified.service;

import net.lesscoding.unified.core.model.dto.activemq.ActiveMqJolokiaQueueQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueInfo;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueMessage;

import java.util.List;

/**
 * @author eleven
 * @date 2025/5/9 15:42
 * @apiNote
 */
public interface ActiveMqService {
    List<QueueInfo> queueList(ActiveMqJolokiaQueueQueryDto dto);

    List<QueueMessage> queueMsgList(ActiveMqJolokiaQueueQueryDto dto);

    Boolean addQueue(ActiveMqJolokiaQueueQueryDto dto);
}
