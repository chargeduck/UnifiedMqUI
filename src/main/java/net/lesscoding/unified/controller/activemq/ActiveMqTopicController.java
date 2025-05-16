package net.lesscoding.unified.controller.activemq;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.core.model.Result;
import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.core.model.dto.activemq.SendMessageDto;
import net.lesscoding.unified.core.model.dto.activemq.SubscriberQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.subscriber.SubscriberInfo;
import net.lesscoding.unified.service.activemq.ActiveMqTopicService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eleven
 * @date 2025/5/13 14:17
 * @apiNote
 */
@RestController
@RequestMapping("/activemq/topic")
@RequiredArgsConstructor
public class ActiveMqTopicController {

    private final ActiveMqTopicService topicService;
    @PostMapping("/sendMessage")
    public Result<Boolean> sendMessage(@RequestBody CommonQueryDto<SendMessageDto> dto) {
        return Result.success(topicService.sendMessage(dto));
    }

    @PostMapping("/activeSubscribers")
    public Result<Page<SubscriberInfo>> subscriberPage(@RequestBody CommonQueryDto<SubscriberQueryDto> dto) {
        return Result.success(topicService.subscriberPage(dto));
    }
}
