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
import net.lesscoding.unified.core.model.dto.activemq.MessageQueryDto;
import net.lesscoding.unified.core.model.dto.activemq.SendMessageDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.ActiveMqJolokiaResponse;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueMessage;
import net.lesscoding.unified.entity.ConnectConfig;
import net.lesscoding.unified.service.activemq.ActiveMqQueueService;
import net.lesscoding.unified.utils.PageUtil;
import net.lesscoding.unified.utils.activemq.JolokiaUtil;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<QueueMessage> queueMsgList(CommonQueryDto<String> dto) {
        ConnectConfig config = dto.getConfig();
        String mBean = StrUtil.format(MbeanFormat.QUEUE_OP.getFormat(), config.getBrokerName(), dto.getParams());
        ActiveMqJolokiaResponse<List<QueueMessage>> response = jolokiaUtil.doArgsMethodWithTypeToken(config,
                JolokiaExecuteType.EXEC,
                mBean,
                ActiveMqMethod.QUEUE_BROWSE,
                null,
                new TypeToken<ActiveMqJolokiaResponse<List<QueueMessage>>>() {
                }
        );
        return response.getValue();
    }

    /**
     * 暂停队列
     * <pre>
     * {
     *     "type": "exec",
     *     "mbean": "org.apache.activemq:brokerName=localhost,destinationName=TestQueue,destinationType=Queue,type=Broker",
     *     "operation": "pause()"
     * }
     * </pre>
     *
     * @param dto query
     * @return result
     */
    @Override
    public Boolean pauseQueue(CommonQueryDto<String> dto) {
        ConnectConfig config = dto.getConfig();
        String mBean = StrUtil.format(MbeanFormat.QUEUE_OP.getFormat(), config.getBrokerName(), dto.getParams());
        return jolokiaUtil.doVoidMethod(config, JolokiaExecuteType.EXEC, mBean, ActiveMqMethod.QUEUE_PAUSE, Object.class);
    }

    /**
     * 恢复队列
     * <pre>
     * {
     *     "type": "exec",
     *     "mbean": "org.apache.activemq:brokerName=localhost,destinationName=TestQueue,destinationType=Queue,type=Broker",
     *     "operation": "resume()"
     * }
     * </pre>
     *
     * @param dto query
     * @return result
     */
    @Override
    public Boolean resumeQueue(CommonQueryDto<String> dto) {
        ConnectConfig config = dto.getConfig();
        String mBean = StrUtil.format(MbeanFormat.QUEUE_OP.getFormat(), config.getBrokerName(), dto.getParams());
        return jolokiaUtil.doVoidMethod(config, JolokiaExecuteType.EXEC, mBean, ActiveMqMethod.QUEUE_RESUME, Object.class);
    }

    /**
     * 清空队列
     * <pre>
     * {
     *     "type": "exec",
     *     "mbean": "org.apache.activemq:brokerName=localhost,destinationName=TestQueue,destinationType=Queue,type=Broker",
     *     "operation": "purge()"
     * }
     * </pre>
     *
     * @param dto query
     * @return result
     */
    @Override
    public Boolean purgeQueue(CommonQueryDto<String> dto) {
        ConnectConfig config = dto.getConfig();
        String mBean = StrUtil.format(MbeanFormat.QUEUE_OP.getFormat(), config.getBrokerName(), dto.getParams());
        return jolokiaUtil.doVoidMethod(config, JolokiaExecuteType.EXEC, mBean, ActiveMqMethod.QUEUE_PURGE, Object.class);
    }

    /**
     * 队列消息分页
     *
     * @param dto query
     * @return result
     */
    @Override
    public Page queueMsgPage(CommonQueryDto<MessageQueryDto> dto) {
        PageDTO<MessageQueryDto> page = dto.getPage();
        MessageQueryDto params = dto.getParams();
        ConnectConfig config = dto.getConfig();
        String mBean = StrUtil.format(MbeanFormat.QUEUE_OP.getFormat(), config.getBrokerName(), params.getName());
        ActiveMqJolokiaResponse<List<QueueMessage>> response = jolokiaUtil.doArgsMethodWithTypeToken(config,
                JolokiaExecuteType.EXEC,
                mBean,
                ActiveMqMethod.QUEUE_BROWSE,
                null,
                new TypeToken<ActiveMqJolokiaResponse<List<QueueMessage>>>() {
                }
        );
        List<QueueMessage> queueMsgList = response.getValue();

        // 合并过滤条件
        queueMsgList = queueMsgList.stream()
                .filter(m -> {
                    boolean textMatch = StrUtil.isBlank(params.getText()) || m.getText().contains(params.getText());
                    boolean startTimeMatch = params.getStartTime() == null || !m.getSendTime().isBefore(params.getStartTime());
                    boolean endTimeMatch = params.getEndTime() == null || !m.getSendTime().isAfter(params.getEndTime());
                    return textMatch && startTimeMatch && endTimeMatch;
                })
                .collect(Collectors.toList());
        if (CollUtil.isNotEmpty(queueMsgList)) {
            return PageUtil.getPageByGetter(queueMsgList, page::getCurrent, page::getSize);
        }
        return page;
    }

    /**
     * 移除消息
     *
     * @param dto query
     * @return result
     */
    @Override
    public Boolean removeMessage(CommonQueryDto<MessageQueryDto> dto) {
        ConnectConfig config = dto.getConfig();
        MessageQueryDto params = dto.getParams();
        String mbean = StrUtil.format(MbeanFormat.QUEUE_OP.getFormat(), config.getBrokerName(), params.getName());
        ActiveMqJolokiaResponse<Object> response = jolokiaUtil.doArgsMethod(config,
                JolokiaExecuteType.EXEC,
                mbean,
                ActiveMqMethod.QUEUE_REMOVE_MESSAGE,
                Collections.singletonList(params.getMessageId()),
                Object.class);
        return response.getStatus() == 200;
    }

    @Override
    public Boolean sendMessage(CommonQueryDto<SendMessageDto> dto) {
        ConnectConfig config = dto.getConfig();
        SendMessageDto params = dto.getParams();
        String mbean = StrUtil.format(MbeanFormat.QUEUE_OP.getFormat(), config.getBrokerName(), params.getName());
        ActiveMqJolokiaResponse<Object> response = jolokiaUtil.doArgsMethod(config,
                JolokiaExecuteType.EXEC,
                mbean,
                ActiveMqMethod.QUEUE_SEND_TEXT_MESSAGE_WITH_PROPERTIES,
                Collections.singletonList(params.getProperties()),
                Object.class);
        return response.getStatus() == 200;
    }
}
