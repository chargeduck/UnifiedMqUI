package net.lesscoding.unified.controller.activemq;

import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.core.model.Result;
import net.lesscoding.unified.core.model.dto.activemq.QueueQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueMessage;
import net.lesscoding.unified.service.activemq.ActiveMqQueueService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author eleven
 * @date 2025/5/13 14:11
 * @apiNote
 */
@RestController
@RequestMapping("/activemq/queue")
@RequiredArgsConstructor
public class ActiveMqQueueController {
    
    private final ActiveMqQueueService activeMqQueueService;
    @PostMapping("/messages")
    public Result<List<QueueMessage>> queueMsgList(@RequestBody QueueQueryDto dto) {
        return Result.success(activeMqQueueService.queueMsgList(dto));
    }

    @PostMapping("/pause")
    public Result<Boolean> queuePause(@RequestBody QueueQueryDto dto) {
        return Result.success(activeMqQueueService.pauseQueue(dto));
    }

    @PostMapping("/resume")
    public Result<Boolean> resumeQueue(@RequestBody QueueQueryDto dto) {
        return Result.success(activeMqQueueService.resumeQueue(dto));
    }

    @PostMapping("/purge")
    public Result<Boolean> purgeQueue(@RequestBody QueueQueryDto dto) {
        return Result.success(activeMqQueueService.purgeQueue(dto));
    }
}
