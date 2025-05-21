package net.lesscoding.unified.controller.activemq;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.core.model.Result;
import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.core.model.dto.activemq.SubscriberQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.SubscriberInfo;
import net.lesscoding.unified.service.activemq.ActiveMqSubscriberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eleven
 * @date 2025/5/16 13:49
 * @apiNote
 */
@RestController
@RequestMapping("/activemq/subscriber")
@RequiredArgsConstructor
public class ActiveMqSubscriberController {
    private final ActiveMqSubscriberService subscriberService;

    @PostMapping("/page")
    @Deprecated
    public Result<Page<SubscriberInfo>> subscriberPage(@RequestBody CommonQueryDto<SubscriberQueryDto> dto) {
        return Result.success(subscriberService.subscriberInfoPage(dto));
    }

    @PostMapping("/conditionPage")
    public Result<Page<SubscriberInfo>> subscriberConditionPage(@RequestBody CommonQueryDto<SubscriberQueryDto> dto) {
        return Result.success(subscriberService.subscriberConditionPage(dto));
    }
}
