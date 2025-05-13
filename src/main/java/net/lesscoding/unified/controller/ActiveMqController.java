package net.lesscoding.unified.controller;

import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.core.model.Result;
import net.lesscoding.unified.core.model.dto.activemq.ActiveMqJolokiaQueueQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueInfo;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueMessage;
import net.lesscoding.unified.service.ActiveMqService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // #region broker操作开始
    @PostMapping("/queues")
    public Result<List<QueueInfo>> queues(@RequestBody ActiveMqJolokiaQueueQueryDto dto) {
        return Result.success(activeMqService.queueList(dto));
    }

    @DeleteMapping("/removeQueue")
    public Result<Boolean> removeQueue(@RequestBody ActiveMqJolokiaQueueQueryDto dto) {
        return Result.success(activeMqService.removeQueue(dto));
    }

    @PostMapping("/addQueue")
    public Result<Boolean> addQueue(@RequestBody ActiveMqJolokiaQueueQueryDto dto) {
        return Result.success(activeMqService.addQueue(dto));
    }
    // #endregion broker操作结束

    // #region queue操作开始
    @PostMapping("/queue/messages")
    public Result<List<QueueMessage>> queueMsgList(@RequestBody ActiveMqJolokiaQueueQueryDto dto) {
        return Result.success(activeMqService.queueMsgList(dto));
    }

    @PostMapping("/queue/pause")
    public Result<Boolean> queuePause(@RequestBody ActiveMqJolokiaQueueQueryDto dto) {
        return Result.success(activeMqService.pauseQueue(dto));
    }

    @PostMapping("/queue/resume")
    public Result<Boolean> resumeQueue(@RequestBody ActiveMqJolokiaQueueQueryDto dto) {
        return Result.success(activeMqService.resumeQueue(dto));
    }

    @PostMapping("/queue/purge")
    public Result<Boolean> purgeQueue(@RequestBody ActiveMqJolokiaQueueQueryDto dto) {
        return Result.success(activeMqService.purgeQueue(dto));
    }
    // #endregion queue操作结束
}
