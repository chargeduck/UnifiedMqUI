package net.lesscoding.unified.utils.activemq;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lesscoding.unified.core.exception.MqException;
import net.lesscoding.unified.core.model.dto.activemq.ActiveMqJolokiaDto;
import net.lesscoding.unified.core.model.dto.activemq.ActiveMqJolokiaQueueQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.ActiveMqJolokiaResponse;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueInfo;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.queue.QueueMessage;
import net.lesscoding.unified.entity.ConnectConfig;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author eleven
 * @date 2025/4/28 14:12
 * @apiNote
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JolokiaUtil {
    private final Gson gson;

    public Object getBrokerList(ConnectConfig connectConfig) {
        ActiveMqJolokiaDto dto = new ActiveMqJolokiaDto()
                .setType("read")
                .setMbean("org.apache.activemq:type=Broker");
        return getJolokiaResponse(connectConfig, dto);
    }

    public String getBrokerInfo(ConnectConfig connectConfig) {
        ActiveMqJolokiaDto dto = new ActiveMqJolokiaDto()
                .setType("read")
                .setMbean("org.apache.activemq:type=Broker,brokerName=" + connectConfig.getBrokerName());
        return getJolokiaResponse(connectConfig, dto);
    }

    public ActiveMqJolokiaResponse<Map<String, QueueInfo>> getQueueList(ActiveMqJolokiaQueueQueryDto queryDto) {
        ConnectConfig config = queryDto.getConfig();
        String queryQueueName = StrUtil.isBlank(queryDto.getQueueName()) ? "*" : StrUtil.format("*{}*", queryDto.getQueueName());
        String mBean = StrUtil.format("org.apache.activemq:type=Broker,brokerName={},destinationType=Queue,destinationName={}",
                config.getBrokerName(),
                queryQueueName);
        ActiveMqJolokiaDto dto = new ActiveMqJolokiaDto()
                .setType("read")
                .setMbean(mBean);
        String response = getJolokiaResponse(config, dto);
        return gson.fromJson(response, new TypeToken<ActiveMqJolokiaResponse<Map<String, QueueInfo>>>() {
        }.getType());
    }


    private String getJolokiaResponse(ConnectConfig connectConfig, ActiveMqJolokiaDto dto) {
        // ActiveMQ Jolokia REST API 端点
        String url = StrUtil.format("http://{}:{}/api/jolokia/", connectConfig.getHost(), 8161);
        // 发送 POST 请求
        HttpResponse response = HttpRequest.post(url)
                .header("Origin", "*")
                .basicAuth(connectConfig.getUsername(), connectConfig.getPassword())
                .body(gson.toJson(dto))
                .execute();

        // 检查响应状态码
        if (response.isOk()) {
            return response.body();
        } else {
            log.error("请求失败，状态码: " + response.getStatus());
            throw new MqException("连接ActiveMQ失败，请检查jolokia配置是否开启");
        }
    }

    /**
     * 获取 Queue 的 Message 列表
     * <pre>
     * {
     *     "type": "exec",
     *     "mbean": "org.apache.activemq:type=Broker,brokerName=localhost,destinationType=Queue,destinationName=TestQueue",
     *     "operation": "browse()"
     * }
     * </pre>
     * @param config 连接配置
     * @return {@link List}   - Queue 的 Message 列表
     */
    public List<QueueMessage> getQueueMsgList(ConnectConfig config, String queueName) {
        String mBean = StrUtil.format("org.apache.activemq:type=Broker,brokerName={},destinationType=Queue,destinationName={}", config.getBrokerName(), queueName);
        ActiveMqJolokiaDto dto = new ActiveMqJolokiaDto()
                .setType("exec")
                .setMbean(mBean)
                .setOperation("browse()");
        String response = getJolokiaResponse(config, dto);
        ActiveMqJolokiaResponse<List<QueueMessage>> responseDto = gson.fromJson(response, new TypeToken<ActiveMqJolokiaResponse<List<QueueMessage>>>() {
        }.getType());
        return responseDto.getValue();
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
     * @param dto
     * @return
     */
    public Boolean addQueue(ActiveMqJolokiaQueueQueryDto dto) {
        ConnectConfig config = dto.getConfig();
        String mBean = StrUtil.format("org.apache.activemq:type=Broker,brokerName={}", config.getBrokerName());
        ActiveMqJolokiaDto jolokiaDto = new ActiveMqJolokiaDto()
               .setType("exec")
               .setMbean(mBean)
               .setOperation("addQueue(java.lang.String)")
                .setArguments(Collections.singletonList(dto.getQueueName()));
        String response = getJolokiaResponse(config, jolokiaDto);
        ActiveMqJolokiaResponse<Object> responseDto = gson.fromJson(response, new TypeToken<ActiveMqJolokiaResponse<Object>>() {
        }.getType());
        return responseDto.getStatus() == 200;
    }
}
