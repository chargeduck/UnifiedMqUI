package net.lesscoding.unified.service.impl;

import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.core.model.dto.activemq.ActiveMqJolokiaQueueQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.ActiveMqJolokiaResponse;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueInfo;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueMessage;
import net.lesscoding.unified.service.ActiveMqService;
import net.lesscoding.unified.utils.activemq.JolokiaUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author eleven
 * @date 2025/5/9 15:43
 * @apiNote
 */
@Service
@RequiredArgsConstructor
public class ActiveMqServiceImpl implements ActiveMqService {
    private final JolokiaUtil jolokiaUtil;


    @Override
    public List<QueueInfo> queueList(ActiveMqJolokiaQueueQueryDto dto) {
        ActiveMqJolokiaResponse<Map<String, QueueInfo>> list = jolokiaUtil.getQueueList(dto.getConfig());
        return new ArrayList<>(list.getValue().values());

    }

    @Override
    public List<QueueMessage> queueMsgList(ActiveMqJolokiaQueueQueryDto dto) {
        return jolokiaUtil.getQueueMsgList(dto.getConfig(), dto.getQueueName());

    }
}
