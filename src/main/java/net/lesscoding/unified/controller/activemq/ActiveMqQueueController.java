package net.lesscoding.unified.controller.activemq;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.core.model.Result;
import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.core.model.dto.activemq.MessageQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueMessage;
import net.lesscoding.unified.service.activemq.ActiveMqQueueService;
import org.springframework.web.bind.annotation.*;

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
    public Result<List<QueueMessage>> queueMsgList(@RequestBody CommonQueryDto<String> dto) {
        return Result.success(activeMqQueueService.queueMsgList(dto));
    }

    @PostMapping("/messagesPage")
    public Result<Page> messagesPage(@RequestBody CommonQueryDto<MessageQueryDto> dto) {
        return Result.success(activeMqQueueService. queueMsgPage(dto));
    }

    @PostMapping("/pause")
    public Result<Boolean> queuePause(@RequestBody CommonQueryDto<String> dto) {
        return Result.success(activeMqQueueService.pauseQueue(dto));
    }

    @PostMapping("/resume")
    public Result<Boolean> resumeQueue(@RequestBody CommonQueryDto<String> dto) {
        return Result.success(activeMqQueueService.resumeQueue(dto));
    }

    @PostMapping("/purge")
    public Result<Boolean> purgeQueue(@RequestBody CommonQueryDto<String> dto) {
        return Result.success(activeMqQueueService.purgeQueue(dto));
    }

    @DeleteMapping("/removeMessage")
    public Result<Boolean> removeMessage(@RequestBody CommonQueryDto<MessageQueryDto> dto) {
        return Result.success(activeMqQueueService.removeMessage(dto));
    }
}
