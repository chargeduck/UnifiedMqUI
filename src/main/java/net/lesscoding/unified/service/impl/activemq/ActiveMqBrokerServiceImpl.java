package net.lesscoding.unified.service.impl.activemq;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.core.model.dto.activemq.QueueQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.ActiveMqJolokiaResponse;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueInfo;
import net.lesscoding.unified.service.activemq.ActiveMqBrokerService;
import net.lesscoding.unified.utils.activemq.JolokiaUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author eleven
 * @date 2025/5/13 14:14
 * @apiNote
 */
@Service
@RequiredArgsConstructor
public class ActiveMqBrokerServiceImpl implements ActiveMqBrokerService {
    private final JolokiaUtil jolokiaUtil;

    @Override
    public List<QueueInfo> queueList(QueueQueryDto dto) {
        ActiveMqJolokiaResponse<Map<String, QueueInfo>> list = jolokiaUtil.getQueueList(dto);
        Map<String, QueueInfo> value = list.getValue();
        return CollUtil.isNotEmpty(value) ?
                new ArrayList<>(value.values()) :
                new ArrayList<>();

    }

    @Override
    public Boolean addQueue(QueueQueryDto dto) {
        return jolokiaUtil.addQueue(dto);
    }

    @Override
    public Boolean removeQueue(QueueQueryDto dto) {
        return jolokiaUtil.removeQueue(dto);
    }

}
