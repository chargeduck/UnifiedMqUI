package net.lesscoding.unified.controller.activemq;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.core.model.Result;
import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueInfo;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.topic.TopicInfo;
import net.lesscoding.unified.service.activemq.ActiveMqBrokerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author eleven
 * @date 2025/5/13 14:10
 * @apiNote
 */
@RestController
@RequestMapping("/activemq/broker")
@RequiredArgsConstructor
public class ActiveMqBrokerController {

    private final ActiveMqBrokerService brokerService;

    /**
     * 队列列表
     *
     * @param dto
     * @return
     */
    @PostMapping("/queues")
    public Result<List<QueueInfo>> queues(@RequestBody CommonQueryDto<String> dto) {
        return Result.success(brokerService.queueList(dto));
    }

    /**
     * 移除队列
     *
     * @param dto
     * @return
     */
    @DeleteMapping("/removeQueue")
    public Result<Boolean> removeQueue(@RequestBody CommonQueryDto<String> dto) {
        return Result.success(brokerService.removeQueue(dto));
    }

    /**
     * 添加队列
     *
     * @param dto
     * @return
     */
    @PostMapping("/addQueue")
    public Result<Boolean> addQueue(@RequestBody CommonQueryDto<String> dto) {
        return Result.success(brokerService.addQueue(dto));
    }

    @PostMapping("/topics")
    public Result<Page<TopicInfo>> topics(@RequestBody CommonQueryDto<String> dto) {
        return Result.success(brokerService.topicList(dto));
    }

    @PostMapping("/addTopic")
    public Result<Boolean> addTopic(@RequestBody CommonQueryDto<String> dto) {
        return Result.success(brokerService.addTopic(dto));
    }

    @DeleteMapping("/removeTopic")
    public Result<Boolean> removeTopic(@RequestBody CommonQueryDto<String> dto) {
        return Result.success(brokerService.removeTopic(dto));
    }
}
