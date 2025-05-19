package net.lesscoding.unified.service.impl.activemq;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.google.common.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.core.enums.activemq.ActiveMqMethod;
import net.lesscoding.unified.core.enums.activemq.JolokiaExecuteType;
import net.lesscoding.unified.core.enums.activemq.MbeanFormat;
import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.core.model.dto.activemq.DurableSubscribeDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.ActiveMqJolokiaResponse;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueInfo;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.topic.TopicInfo;
import net.lesscoding.unified.entity.ConnectConfig;
import net.lesscoding.unified.service.activemq.ActiveMqBrokerService;
import net.lesscoding.unified.utils.PageUtil;
import net.lesscoding.unified.utils.activemq.JolokiaUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
    public List<QueueInfo> queueList(CommonQueryDto<String> dto) {
        ConnectConfig config = dto.getConfig();
        String queryQueueName = StrUtil.isBlank(dto.getParams()) ? "*" : StrUtil.format("*{}*", dto.getParams());
        String mBean = StrUtil.format(MbeanFormat.QUEUE_OP.getFormat(), config.getBrokerName(), queryQueueName);
        ActiveMqJolokiaResponse<Map<String, QueueInfo>> list = jolokiaUtil.doArgsMethodWithTypeToken(config,
                JolokiaExecuteType.READ,
                mBean,
                ActiveMqMethod.NONE,
                null,
                new TypeToken<ActiveMqJolokiaResponse<Map<String, QueueInfo>>>() {
                }
        );
        Map<String, QueueInfo> value = list.getValue();
        return CollUtil.isNotEmpty(value) ?
                new ArrayList<>(value.values()) :
                new ArrayList<>();
    }

    /**
     * <pre>
     * {
     *     "type": "exec",
     *     "mbean": "org.apache.activemq:type=Broker,brokerName=localhost",
     *     "operation": "addQueue(java.lang.String)",
     *     "arguments": [
     *         "Name"
     *     ]
     *  }
     * </pre>
     *
     * @param dto query
     * @return result
     */
    @Override
    public Boolean addQueue(CommonQueryDto<String> dto) {
        ConnectConfig config = dto.getConfig();
        String mBean = StrUtil.format(MbeanFormat.BROKER_OP.getFormat(), config.getBrokerName());
        ActiveMqJolokiaResponse<Object> response = jolokiaUtil.doArgsMethod(config,
                JolokiaExecuteType.EXEC,
                mBean,
                ActiveMqMethod.BROKER_ADD_QUEUE,
                Collections.singletonList(dto.getParams()),
                Object.class

        );
        return response.getStatus() == 200;
    }

    @Override
    public Page<TopicInfo> topicList(CommonQueryDto<String> dto) {
        ConnectConfig config = dto.getConfig();
        String queryTopicName = StrUtil.isBlank(dto.getParams()) ? "*" : StrUtil.format("*{}*", dto.getParams());
        String mBean = StrUtil.format(MbeanFormat.TOPIC_OP.getFormat(), config.getBrokerName(), queryTopicName);
        ActiveMqJolokiaResponse<Map<String, TopicInfo>> list = jolokiaUtil.doArgsMethodWithTypeToken(config,
                JolokiaExecuteType.READ,
                mBean,
                ActiveMqMethod.NONE,
                null,
                new TypeToken<ActiveMqJolokiaResponse<Map<String, TopicInfo>>>() {
                }
        );
        Map<String, TopicInfo> value = list.getValue();
        List<TopicInfo> filterList = CollUtil.isNotEmpty(value) ?
                new ArrayList<>(value.values()) :
                new ArrayList<>();
        PageDTO<String> page = dto.getPage();
        return new PageUtil<TopicInfo>()
                .getPageByGetter(
                        filterList,
                        page::getCurrent,
                        page::getSize
                );
    }

    @Override
    public Boolean removeTopic(CommonQueryDto<String> dto) {
        ConnectConfig config = dto.getConfig();
        String mBean = StrUtil.format(MbeanFormat.BROKER_OP.getFormat(), config.getBrokerName());
        ActiveMqJolokiaResponse<Object> response = jolokiaUtil.doArgsMethod(config,
                JolokiaExecuteType.EXEC,
                mBean,
                ActiveMqMethod.BROKER_REMOVE_TOPIC,
                Collections.singletonList(dto.getParams()),
                Object.class

        );
        return response.getStatus() == 200;
    }

    @Override
    public Boolean addTopic(CommonQueryDto<String> dto) {
        ConnectConfig config = dto.getConfig();
        String mBean = StrUtil.format(MbeanFormat.BROKER_OP.getFormat(), config.getBrokerName());
        ActiveMqJolokiaResponse<Object> response = jolokiaUtil.doArgsMethod(config,
                JolokiaExecuteType.EXEC,
                mBean,
                ActiveMqMethod.BROKER_ADD_TOPIC,
                Collections.singletonList(dto.getParams()),
                Object.class

        );
        return response.getStatus() == 200;
    }

    /**
     * <pre>
     * {
     *     "type": "exec",
     *     "mbean": "org.apache.activemq:type=Broker,brokerName=localhost",
     *     "operation": "removeQueue(java.lang.String)",
     *     "arguments": [
     *         "Name"
     *     ]
     *  }
     * </pre>
     *
     * @param dto
     * @return
     */
    @Override
    public Boolean removeQueue(CommonQueryDto<String> dto) {
        ConnectConfig config = dto.getConfig();
        String mBean = StrUtil.format(MbeanFormat.BROKER_OP.getFormat(), config.getBrokerName());
        ActiveMqJolokiaResponse<Object> response = jolokiaUtil.doArgsMethod(config,
                JolokiaExecuteType.EXEC,
                mBean,
                ActiveMqMethod.BROKER_REMOVE_QUEUE,
                Collections.singletonList(dto.getParams()),
                Object.class

        );
        return response.getStatus() == 200;
    }

    @Override
    public Boolean createDurableSubscriber(CommonQueryDto<DurableSubscribeDto> dto) {
        ConnectConfig config = dto.getConfig();
        DurableSubscribeDto params = dto.getParams();
        String mBean = StrUtil.format(MbeanFormat.BROKER_OP.getFormat(), config.getBrokerName());
        List<String> args = new ArrayList<>();
        args.add(params.getClientId());
        args.add(params.getSubscriptionName());
        args.add(params.getTopicName());
        args.add(params.getSelector());
        ActiveMqJolokiaResponse<Object> response = jolokiaUtil.doArgsMethod(config,
                JolokiaExecuteType.EXEC,
                mBean,
                ActiveMqMethod.BROKER_ADD_DURABLE_SUBSCRIBER,
                args,
                Object.class
        );
        return response.getStatus() == 200;
    }
}
