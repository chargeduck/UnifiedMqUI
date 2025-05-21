package net.lesscoding.unified.service.impl.activemq;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.core.enums.activemq.ActiveMqMethod;
import net.lesscoding.unified.core.enums.activemq.JolokiaExecuteType;
import net.lesscoding.unified.core.enums.activemq.MbeanFormat;
import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.core.model.dto.activemq.SendMessageDto;
import net.lesscoding.unified.core.model.dto.activemq.SubscriberQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.ActiveMqJolokiaResponse;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.SubscriberInfo;
import net.lesscoding.unified.entity.ConnectConfig;
import net.lesscoding.unified.service.activemq.ActiveMqSubscriberService;
import net.lesscoding.unified.service.activemq.ActiveMqTopicService;
import net.lesscoding.unified.utils.activemq.JolokiaUtil;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author eleven
 * @date 2025/5/15 13:20
 * @apiNote
 */
@Service
@RequiredArgsConstructor
public class ActiveMqTopicServiceImpl implements ActiveMqTopicService {
    private final JolokiaUtil jolokiaUtil;
    private final ActiveMqSubscriberService subscriberService;

    @Override
    public Boolean sendMessage(CommonQueryDto<SendMessageDto> dto) {
        ConnectConfig config = dto.getConfig();
        SendMessageDto params = dto.getParams();
        String mbean = StrUtil.format(MbeanFormat.TOPIC_OP.getFormat(), config.getBrokerName(), params.getName());
        ActiveMqJolokiaResponse<Object> response = jolokiaUtil.doArgsMethod(config,
                JolokiaExecuteType.EXEC,
                mbean,
                ActiveMqMethod.TOPIC_SEND_TEXT_MESSAGE_WITH_PROPERTIES,
                Collections.singletonList(params.getProperties()),
                Object.class);
        return response.getStatus() == 200;
    }

    @Override
    public Page<SubscriberInfo> subscriberPage(CommonQueryDto<SubscriberQueryDto> dto) {
        return subscriberService.subscriberInfoPage(dto);
    }
}
