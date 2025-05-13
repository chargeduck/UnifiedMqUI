package net.lesscoding.unified.service.impl.activemq;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.core.model.dto.activemq.QueueQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueMessage;
import net.lesscoding.unified.entity.ConnectConfig;
import net.lesscoding.unified.service.activemq.ActiveMqQueueService;
import net.lesscoding.unified.utils.activemq.JolokiaUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author eleven
 * @date 2025/5/13 14:26
 * @apiNote
 */
@Service
@RequiredArgsConstructor
public class ActiveMqQueueServiceImpl implements ActiveMqQueueService {

    private final JolokiaUtil jolokiaUtil;

    @Override
    public List<QueueMessage> queueMsgList(QueueQueryDto<String> dto) {
        return jolokiaUtil.getQueueMsgList(dto.getConfig(), dto.getParams());

    }

    /**
     * <pre>
     * {

     }
    @Override
    public List<QueueMessage> queueMsgList(QueueQueryDto dto) {
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
    public Boolean pauseQueue(QueueQueryDto<String> dto) {
        ConnectConfig config = dto.getConfig();
        String mBean = StrUtil.format("org.apache.activemq:brokerName={},type=Broker,destinationName={},destinationType=Queue",
                config.getBrokerName(),
                dto.getParams());
        return jolokiaUtil.doVoidMethod(config, "exec", mBean, "pause()", Object.class);
    }

    @Override
    public Boolean resumeQueue(QueueQueryDto<String> dto) {
        ConnectConfig config = dto.getConfig();
        String mBean = StrUtil.format("org.apache.activemq:brokerName={},type=Broker,destinationName={},destinationType=Queue",
                config.getBrokerName(),
                dto.getParams());
        return jolokiaUtil.doVoidMethod(config, "exec", mBean, "resume()", Object.class);
    }

    @Override
    public Boolean purgeQueue(QueueQueryDto<String> dto) {
        ConnectConfig config = dto.getConfig();
        String mBean = StrUtil.format("org.apache.activemq:brokerName={},type=Broker,destinationName={},destinationType=Queue",
                config.getBrokerName(),
                dto.getParams());
        return jolokiaUtil.doVoidMethod(config, "exec", mBean, "purge()", Object.class);
    }
}
