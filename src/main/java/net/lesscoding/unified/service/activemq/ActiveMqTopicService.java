package net.lesscoding.unified.service.activemq;

import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.core.model.dto.activemq.SendMessageDto;

/**
 * @author eleven
 * @date 2025/5/15 13:20
 * @apiNote
 */
public interface ActiveMqTopicService {
    Boolean sendMessage(CommonQueryDto<SendMessageDto> dto);
}
