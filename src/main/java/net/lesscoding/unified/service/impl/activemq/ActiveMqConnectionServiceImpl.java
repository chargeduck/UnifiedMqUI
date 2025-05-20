package net.lesscoding.unified.service.impl.activemq;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lesscoding.unified.core.enums.activemq.ActiveMqMethod;
import net.lesscoding.unified.core.enums.activemq.JolokiaExecuteType;
import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.ActiveMqJolokiaResponse;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.ConsumerInfo;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.SearchResult;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.connection.ConnectionInfo;
import net.lesscoding.unified.entity.ConnectConfig;
import net.lesscoding.unified.service.activemq.ActiveMqConnectionService;
import net.lesscoding.unified.utils.PageUtil;
import net.lesscoding.unified.utils.activemq.JolokiaUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author eleven
 * @date 2025/5/19 15:36
 * @apiNote
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ActiveMqConnectionServiceImpl implements ActiveMqConnectionService {
    private final JolokiaUtil jolokiaUtil;
    private final Gson gson;

    /**
     *
     */
    @Override
    public Page<ConnectionInfo> connectionList(CommonQueryDto<String> dto) {
        ConnectConfig config = dto.getConfig();
        String key = "org.apache.activemq";
        PageDTO<String> page = dto.getPage();
        List<String> connectorList = getConnectorObjectNames(config, key);
        log.info("connectorList: {}", gson.toJson(connectorList));
        List<ConnectionInfo> infoList = connectorList.stream()
                .map(item -> {
                    ConnectionInfo info = jolokiaUtil.doArgsMethodWithTypeToken(
                            config,
                            JolokiaExecuteType.READ,
                            StrUtil.format("{}:{}", key, item),
                            ActiveMqMethod.NONE,
                            "",
                            new TypeToken<ActiveMqJolokiaResponse<ConnectionInfo>>() {

                            }
                    ).getValue();
                    info.setConnectorType(getConnectorName(item));
                    return info;
                })
                .filter(item -> StrUtil.isBlank(dto.getParams()) || StrUtil.equals(item.getConnectorType(), dto.getParams()))
                .collect(Collectors.toList());
        return new PageUtil<ConnectionInfo>()
                .getPageByGetter(infoList, page::getCurrent, page::getSize);
    }

    @Override
    public Page<ConsumerInfo> connectionConsumers(CommonQueryDto<List<SearchResult>> dto) {
        List<SearchResult> params = dto.getParams();
        ConnectConfig config = dto.getConfig();
        PageDTO page = dto.getPage();
        List<ConsumerInfo> consumerInfos = params.stream()
                .map(item -> jolokiaUtil.doReadWithTypeToken(
                        config,
                        item.getObjectName(),
                        "",
                        new TypeToken<ActiveMqJolokiaResponse<ConsumerInfo>>() {

                        }
                ).getValue()).collect(Collectors.toList());
        return new PageUtil<ConsumerInfo>()
                .getPageByGetter(consumerInfos, page::getCurrent, page::getSize);
    }

    /**
     * 获取所有的连接对象名称
     * [
     * "brokerName=localhost,connectionName=tcp_//127.0.0.1_50374,connectionViewType=remoteAddress,connector=clientConnectors,connectorName=mqtt,type=Broker",
     * "brokerName=localhost,connectionName=tcp_//127.0.0.1_50409,connectionViewType=remoteAddress,connector=clientConnectors,connectorName=openwire,type=Broker",
     * "brokerName=localhost,connectionName=tcp_//127.0.0.1_50380,connectionViewType=remoteAddress,connector=clientConnectors,connectorName=openwire,type=Broker",
     * "brokerName=localhost,connectionName=tcp_//127.0.0.1_50408,connectionViewType=remoteAddress,connector=clientConnectors,connectorName=openwire,type=Broker",
     * "brokerName=localhost,connectionName=tcp_//127.0.0.1_54753,connectionViewType=remoteAddress,connector=clientConnectors,connectorName=amqp,type=Broker"
     * ]
     *
     * @param config 连接配置
     * @param key    键名
     * @return 连接对象名称列表
     */
    public List<String> getConnectorObjectNames(ConnectConfig config, String key) {
        ActiveMqJolokiaResponse<JsonElement> list = jolokiaUtil.getList(
                config,
                new TypeToken<>() {
                }

        );

        JsonElement element = list.getValue();
        JsonObject mbeanJo = element.getAsJsonObject().get(key).getAsJsonObject();
        return mbeanJo.keySet().stream()
                .filter(item -> item.contains("_//"))
                .collect(Collectors.toList());
    }

    /**
     * 获取connectorName
     *
     * @param objectName 连接对象名称
     * @return 连接器名称
     */
    private String getConnectorName(String objectName) {
        Pattern pattern = Pattern.compile("connectorName=([^,]+)");
        Matcher matcher = pattern.matcher(objectName);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "--";
    }
}
