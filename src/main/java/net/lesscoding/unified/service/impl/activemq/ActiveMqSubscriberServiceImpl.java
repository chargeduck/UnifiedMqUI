package net.lesscoding.unified.service.impl.activemq;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.google.common.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lesscoding.unified.core.enums.activemq.ActiveMqMethod;
import net.lesscoding.unified.core.enums.activemq.JolokiaExecuteType;
import net.lesscoding.unified.core.enums.activemq.MbeanFormat;
import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.core.model.dto.activemq.SubscriberQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.ActiveMqJolokiaResponse;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.SearchResult;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.subscriber.SubscriberInfo;
import net.lesscoding.unified.entity.ConnectConfig;
import net.lesscoding.unified.service.activemq.ActiveMqSubscriberService;
import net.lesscoding.unified.utils.PageUtil;
import net.lesscoding.unified.utils.activemq.JolokiaUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author eleven
 * @date 2025/5/16 13:51
 * @apiNote
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ActiveMqSubscriberServiceImpl implements ActiveMqSubscriberService {
    private final JolokiaUtil jolokiaUtil;

    /**
     * 查询订阅者信息
     *
     * @param config          连接配置
     * @param destinationName 主题名称
     * @return 订阅者信息列表
     */
    @Override
    public ActiveMqJolokiaResponse<List<SearchResult>> brokerSubscriptions(ConnectConfig config, String destinationName) {
        String attribute = "Subscriptions";
        if (StrUtil.isBlank(destinationName)) {
            destinationName = "*";
            String mbean = StrUtil.format(MbeanFormat.TOPIC_OP.getFormat(), config.getBrokerName(), destinationName);
            ActiveMqJolokiaResponse<Map<String, Map<String, List<SearchResult>>>> allResult = jolokiaUtil.doSearchAttribute(
                    config,
                    JolokiaExecuteType.READ,
                    mbean,
                    attribute,
                    new TypeToken<ActiveMqJolokiaResponse<Map<String, Map<String, List<SearchResult>>>>>() {
                    }
            );
            Map<String, Map<String, List<SearchResult>>> allResultValue = allResult.getValue();
            List<SearchResult> responseValue = new ArrayList<>();
            if (CollUtil.isNotEmpty(allResultValue)) {
                allResultValue.values().forEach(item -> {
                    if (CollUtil.isNotEmpty(item.values())) {
                        item.values().forEach(responseValue::addAll);
                    }
                });
            }
            return new ActiveMqJolokiaResponse<List<SearchResult>>(
                    allResult.getRequest(),
                    responseValue,
                    allResult.getTimestamp(),
                    allResult.getStatus()
            );
        } else {
            String mbean = StrUtil.format(MbeanFormat.TOPIC_OP.getFormat(), config.getBrokerName(), destinationName);
            return jolokiaUtil.doSearchAttribute(
                    config,
                    JolokiaExecuteType.READ,
                    mbean,
                    attribute,
                    new TypeToken<ActiveMqJolokiaResponse<List<SearchResult>>>() {
                    }
            );
        }
    }

    /**
     * 查询订阅者信息
     *
     * @param config 连接配置
     * @param dto    查询条件
     * @return 订阅者信息列表
     */
    @Override
    public List<SubscriberInfo> subscriberInfos(ConnectConfig config, SubscriberQueryDto dto) {
        ActiveMqJolokiaResponse<List<SearchResult>> searchResult = brokerSubscriptions(config, dto.getName());
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
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            return subscribersFilter(filterList, dto);
        }
        return new ArrayList<>();
    }

    /**
     * 订阅者信息分页查询
     *
     * @param config 连接配置
     * @param dto    查询条件
     * @param page   分页信息
     * @return 分页结果
     */
    @Override
    public Page<SubscriberInfo> subscriberInfoPage(ConnectConfig config, SubscriberQueryDto dto, Page<?> page) {
        List<SubscriberInfo> list = subscriberInfos(config, dto);
        page = Optional.ofNullable(page).orElse(new PageDTO<>(1, 10));
        return new PageUtil<SubscriberInfo>().getPageByGetter(list, page::getCurrent, page::getSize);
    }


    /**
     * 分页查询订阅者信息
     *
     * @param dto 查询条件
     * @return 分页结果
     */
    @Override
    public Page<SubscriberInfo> subscriberInfoPage(CommonQueryDto<SubscriberQueryDto> dto) {
        return subscriberInfoPage(dto.getConfig(), dto.getParams(), dto.getPage());
    }

    /**
     * 分页查询订阅者信息
     *
     * @param dto 查询条件
     * @return 分页结果
     */
    @Override
    public Page<SubscriberInfo> subscriberConditionPage(CommonQueryDto<SubscriberQueryDto> dto) {
        Map<String, SubscriberInfo> statusMap = subscriberStatusList(dto);
        if (CollUtil.isEmpty(statusMap)) {
            return new Page<>();
        }
        List<SubscriberInfo> values = new ArrayList<>(statusMap.values());
        PageDTO<SubscriberQueryDto> page = dto.getPage();
        SubscriberQueryDto params = dto.getParams();
        if (CollUtil.isNotEmpty(values)) {
            values = subscribersFilter(values, params);
        }
        return new PageUtil<SubscriberInfo>().getPageByGetter(values, page::getCurrent, page::getSize);
    }

    /**
     * 订阅者信息过滤
     *
     * @param values 订阅者信息列表
     * @param params 查询条件
     * @return 过滤后的订阅者信息列表
     */
    private static List<SubscriberInfo> subscribersFilter(List<SubscriberInfo> values, SubscriberQueryDto params) {
        return values.stream()
                .filter(item -> StrUtil.isBlank(params.getSubscriptionName()) ||
                        item.getSubscriptionName().contains(params.getSubscriptionName()))
                .filter(item -> StrUtil.isBlank(params.getDestinationName()) ||
                        item.getDestinationName().contains(params.getDestinationName()))
                .filter(item -> params.getActive() == null || item.getActive() == params.getActive())
                .filter(item -> params.getDurable() == null || item.getDurable() == params.getDurable())
                .collect(Collectors.toList());
    }

    /**
     * <pre>
     * {
     *     "mbean": "org.apache.activemq:type=Broker,brokerName=localhost,destinationType=Topic,destinationName=*,endpoint=Consumer,clientId=*,consumerId=*",
     *     "type": "read",
     *     "attribute": [
     *         "Active",
     *         "Durable"
     *     ]
     * }
     * </pre>
     *
     * @param dto
     * @return
     */
    public Map<String, SubscriberInfo> subscriberStatusList(CommonQueryDto<SubscriberQueryDto> dto) {
        ConnectConfig config = dto.getConfig();
        String mbean = StrUtil.format(MbeanFormat.SUBSCRIBER_OP.getFormat(), config.getBrokerName());
        ActiveMqJolokiaResponse<Map<String, SubscriberInfo>> statusResponse = jolokiaUtil.doArgsMethodWithTypeToken(config,
                JolokiaExecuteType.READ,
                mbean,
                ActiveMqMethod.NONE,
                null,
                new TypeToken<ActiveMqJolokiaResponse<Map<String, SubscriberInfo>>>() {
                }
        );
        return statusResponse.getValue();
    }
}
