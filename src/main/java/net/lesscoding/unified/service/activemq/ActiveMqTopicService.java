package net.lesscoding.unified.service.activemq;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.core.model.dto.activemq.SendMessageDto;
import net.lesscoding.unified.core.model.dto.activemq.SubscriberQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.SubscriberInfo;

/**
 * @author eleven
 * @date 2025/5/15 13:20
 * @apiNote
 */
public interface ActiveMqTopicService {
    Boolean sendMessage(CommonQueryDto<SendMessageDto> dto);

    Page<SubscriberInfo> subscriberPage(CommonQueryDto<SubscriberQueryDto> dto);
}
