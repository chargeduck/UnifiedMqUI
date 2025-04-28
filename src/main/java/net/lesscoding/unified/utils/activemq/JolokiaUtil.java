package net.lesscoding.unified.utils.activemq;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lesscoding.unified.core.exception.MqException;
import net.lesscoding.unified.core.model.dto.ActiveMqJolokiaDto;
import net.lesscoding.unified.entity.ConnectConfig;
import org.springframework.stereotype.Component;

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
}
