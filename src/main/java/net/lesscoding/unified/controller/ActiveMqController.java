package net.lesscoding.unified.controller;

import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.core.model.Result;
import net.lesscoding.unified.core.model.dto.activemq.ActiveMqJolokiaQueueQueryDto;
import net.lesscoding.unified.service.ActiveMqService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eleven
 * @date 2025/5/9 14:55
 * @apiNote
 */
@RestController
@RequestMapping("/activemq")
@RequiredArgsConstructor
public class ActiveMqController {
    private final ActiveMqService activeMqService;
    @PostMapping("/queues")
    public Result queues(@RequestBody ActiveMqJolokiaQueueQueryDto dto) {
        return Result.success(activeMqService.queueList(dto));
    }
}
