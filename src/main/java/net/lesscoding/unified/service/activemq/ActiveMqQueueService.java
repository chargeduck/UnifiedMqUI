package net.lesscoding.unified.service.activemq;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.core.model.dto.activemq.MessageQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueMessage;

import java.util.List;

/**
 * @author eleven
 * @date 2025/5/13 14:25
 * @apiNote
 */
public interface ActiveMqQueueService {
    List<QueueMessage> queueMsgList(CommonQueryDto<String> dto);

    Boolean pauseQueue(CommonQueryDto<String> dto);

    Boolean resumeQueue(CommonQueryDto<String> dto);

    Boolean purgeQueue(CommonQueryDto<String> dto);

    Page queueMsgPage(CommonQueryDto<MessageQueryDto> dto);

    Boolean removeMessage(CommonQueryDto<MessageQueryDto> dto);
}
