package net.lesscoding.unified.service.activemq;

import net.lesscoding.unified.core.model.dto.activemq.ActiveMqJolokiaQueueQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueMessage;

import java.util.List;

/**
 * @author eleven
 * @date 2025/5/13 14:25
 * @apiNote
 */
public interface ActiveMqQueueService {
    List<QueueMessage> queueMsgList(ActiveMqJolokiaQueueQueryDto dto);

    Boolean pauseQueue(ActiveMqJolokiaQueueQueryDto dto);

    Boolean resumeQueue(ActiveMqJolokiaQueueQueryDto dto);

    Boolean purgeQueue(ActiveMqJolokiaQueueQueryDto dto);

}
