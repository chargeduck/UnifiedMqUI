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
import net.lesscoding.unified.core.model.dto.activemq.SendMessageDto;
import net.lesscoding.unified.core.model.dto.activemq.SubscriberQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.ActiveMqJolokiaResponse;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.broker.SearchResult;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.subscriber.SubscriberInfo;
import net.lesscoding.unified.entity.ConnectConfig;
import net.lesscoding.unified.service.activemq.ActiveMqTopicService;
import net.lesscoding.unified.utils.PageUtil;
import net.lesscoding.unified.utils.activemq.JolokiaUtil;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eleven
 * @date 2025/5/15 13:20
 * @apiNote
 */
@Service
@RequiredArgsConstructor
public class ActiveMqTopicServiceImpl implements ActiveMqTopicService {
    private final JolokiaUtil jolokiaUtil;

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
        PageDTO<SubscriberQueryDto> page = dto.getPage();
        ConnectConfig config = dto.getConfig();
        SubscriberQueryDto params = dto.getParams();
        String mbean = StrUtil.format(MbeanFormat.TOPIC_OP.getFormat(), config.getBrokerName(), params.getName());
        ActiveMqJolokiaResponse<List<SearchResult>> searchResult = jolokiaUtil.doSearchAttribute(
                config,
                JolokiaExecuteType.READ,
                mbean,
                "Subscriptions",
                new TypeToken<ActiveMqJolokiaResponse<List<SearchResult>>>() {
                }
        );
        List<SearchResult> resultValue = searchResult.getValue();
        if (CollUtil.isNotEmpty(resultValue)) {
            List<SubscriberInfo> filterList = resultValue.stream()
                    .map(item -> jolokiaUtil.doArgsMethodWithTypeToken(
                            config,
                            JolokiaExecuteType.READ,
                            item.getObjectName(),
                            ActiveMqMethod.NONE,
                            null,
                            new TypeToken<ActiveMqJolokiaResponse<SubscriberInfo>>() {
                            }
                    ).getValue())
                    .filter(item -> item.getSubscriptionName().contains(params.getSubscriptionName()))
                    .collect(Collectors.toList());
            return new PageUtil<SubscriberInfo>()
                    .getPageByGetter(filterList,
                            page::getCurrent,
                            page::getSize);
        }
        return new PageDTO<>();
    }
}
