package net.lesscoding.unified.service.activemq;

import net.lesscoding.unified.core.model.dto.activemq.QueueQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueMessage;

import java.util.List;

/**
 * @author eleven
 * @date 2025/5/13 14:25
 * @apiNote
 */
public interface ActiveMqQueueService {
    List<QueueMessage> queueMsgList(QueueQueryDto<String> dto);

    Boolean pauseQueue(QueueQueryDto<String> dto);

    Boolean resumeQueue(QueueQueryDto<String> dto);

    Boolean purgeQueue(QueueQueryDto<String> dto);

}
