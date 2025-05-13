package net.lesscoding.unified.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.core.model.dto.activemq.ActiveMqJolokiaQueueQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.ActiveMqJolokiaResponse;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueInfo;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueMessage;
import net.lesscoding.unified.entity.ConnectConfig;
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
        ActiveMqJolokiaResponse<Map<String, QueueInfo>> list = jolokiaUtil.getQueueList(dto);
        Map<String, QueueInfo> value = list.getValue();
        return CollUtil.isNotEmpty(value) ?
                new ArrayList<>(value.values()) :
                new ArrayList<>();

    }

    @Override
    public Boolean addQueue(ActiveMqJolokiaQueueQueryDto dto) {
        return jolokiaUtil.addQueue(dto);
    }

    @Override
    public Boolean removeQueue(ActiveMqJolokiaQueueQueryDto dto) {
        return jolokiaUtil.removeQueue(dto);
    }


    @Override
    public List<QueueMessage> queueMsgList(ActiveMqJolokiaQueueQueryDto dto) {
        return jolokiaUtil.getQueueMsgList(dto.getConfig(), dto.getQueueName());

    }



    /**
     * <pre>
     * {
     *     "type": "exec",
     *     "mbean": "org.apache.activemq:brokerName=localhost,destinationName=TestQueue,destinationType=Queue,type=Broker",
     *     "operation": "pause()"
     * }
     * </pre>
     * @param dto
     * @return
     */
    @Override
    public Boolean pauseQueue(ActiveMqJolokiaQueueQueryDto dto) {
        ConnectConfig config = dto.getConfig();
        String mBean = StrUtil.format("org.apache.activemq:brokerName={},type=Broker,destinationName={},destinationType=Queue",
                config.getBrokerName(),
                dto.getQueueName());
        return jolokiaUtil.doVoidMethod(config, "exec", mBean, "pause()", Object.class);
    }

    @Override
    public Boolean resumeQueue(ActiveMqJolokiaQueueQueryDto dto) {
        ConnectConfig config = dto.getConfig();
        String mBean = StrUtil.format("org.apache.activemq:brokerName={},type=Broker,destinationName={},destinationType=Queue",
                config.getBrokerName(),
                dto.getQueueName());
        return jolokiaUtil.doVoidMethod(config, "exec", mBean, "resume()", Object.class);
    }

    @Override
    public Boolean purgeQueue(ActiveMqJolokiaQueueQueryDto dto) {
        ConnectConfig config = dto.getConfig();
        String mBean = StrUtil.format("org.apache.activemq:brokerName={},type=Broker,destinationName={},destinationType=Queue",
                config.getBrokerName(),
                dto.getQueueName());
        return jolokiaUtil.doVoidMethod(config, "exec", mBean, "purge()", Object.class);
    }


}
