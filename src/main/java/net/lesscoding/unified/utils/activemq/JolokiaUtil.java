package net.lesscoding.unified.utils.activemq;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lesscoding.unified.core.enums.activemq.ActiveMqMethod;
import net.lesscoding.unified.core.enums.activemq.JolokiaExecuteType;
import net.lesscoding.unified.core.exception.MqException;
import net.lesscoding.unified.core.model.dto.activemq.JolokiaQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.ActiveMqJolokiaResponse;
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
        JolokiaQueryDto dto = new JolokiaQueryDto()
                .setType("read")
                .setMbean("org.apache.activemq:type=Broker");
        return getJolokiaResponse(connectConfig, dto);
    }

    public String getBrokerInfo(ConnectConfig connectConfig) {
        JolokiaQueryDto dto = new JolokiaQueryDto()
                .setType("read")
                .setMbean("org.apache.activemq:type=Broker,brokerName=" + connectConfig.getBrokerName());
        return getJolokiaResponse(connectConfig, dto);
    }

    public <T> T getByQueryDto(ConnectConfig connectConfig, JolokiaQueryDto dto, TypeToken<T> typeToken) {
        String response = getJolokiaResponse(connectConfig, dto);
        log.info("response: {}", response);
        return gson.fromJson(response, typeToken.getType());
    }

    public <T> T getList(ConnectConfig connectConfig, TypeToken<T> typeToken) {
        // ActiveMQ Jolokia REST API 端点
        String url = StrUtil.format("http://{}:{}/api/jolokia/list", connectConfig.getHost(), connectConfig.getExtraPort());
        // 发送 POST 请求
        HttpResponse response = HttpRequest.post(url)
                .header("Origin", "*")
                .basicAuth(connectConfig.getUsername(), connectConfig.getPassword())
                .body(gson.toJson(new JolokiaQueryDto().setType(JolokiaExecuteType.LIST.getType())))
                .execute();
        log.info("response: {}", response.body());
        if (response.isOk()) {
            return gson.fromJson(response.body(), typeToken.getType());
        } else {
            log.error("请求失败，状态码: " + response.getStatus());
            throw new MqException("连接ActiveMQ失败，请检查jolokia配置是否开启");
        }
    }

    private String getJolokiaResponse(ConnectConfig connectConfig, JolokiaQueryDto dto) {
        log.info("本次请求参数为: {}", gson.toJson(dto));
        // ActiveMQ Jolokia REST API 端点
        String url = StrUtil.format("http://{}:{}/api/jolokia/", connectConfig.getHost(), connectConfig.getExtraPort());
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
     * 执行   带参数的方法
     *
     * @param config    连接配置
     * @param execType  类型
     * @param mbean     mbean
     * @param method    方法
     * @param arguments 参数
     * @param clazz     响应类型
     * @param <T>       响应类型
     * @return {@link ActiveMqJolokiaResponse<T>}  - 响应
     */
    public <T> ActiveMqJolokiaResponse<T> doArgsMethod(ConnectConfig config, JolokiaExecuteType execType,
                                                       String mbean, ActiveMqMethod method,
                                                       Object arguments, Class<T> clazz) {
        JolokiaQueryDto dto = new JolokiaQueryDto()
                .setType(execType.getType())
                .setMbean(mbean)
                .setOperation(method.getName())
                .setArguments(arguments);
        String response = getJolokiaResponse(config, dto);
        log.info("response: {}", response);
        ActiveMqJolokiaResponse<T> responseDto = gson.fromJson(response, new TypeToken<ActiveMqJolokiaResponse<T>>() {
        }.getType());
        return responseDto;
    }

    /**
     * 执行   带参数的方法
     *
     * @param config    连接配置
     * @param execType  类型
     * @param mbean     mbean
     * @param method    方法
     * @param arguments 参数
     * @param typeToken gson 类型解析器
     * @param <T>       响应类型
     * @return {@link ActiveMqJolokiaResponse<T>}  - 响应
     */
    public <T> T doArgsMethodWithTypeToken(ConnectConfig config, JolokiaExecuteType execType,
                                           String mbean, ActiveMqMethod method,
                                           Object arguments, TypeToken<T> typeToken) {
        JolokiaQueryDto dto = new JolokiaQueryDto()
                .setType(execType.getType())
                .setMbean(mbean)
                .setOperation(method.getName())
                .setArguments(arguments);
        String response = getJolokiaResponse(config, dto);
        log.info("response: {}", response);
        return gson.fromJson(response, typeToken.getType());
    }

    /**
     * 执行  读取参数的方法
     *
     * @param config    连接配置
     * @param mbean     mbean
     * @param arguments 参数
     * @param typeToken gson 类型解析器
     * @param <T>       响应类型
     * @return T
     */
    public <T> T doReadWithTypeToken(ConnectConfig config, String mbean,
                                     Object arguments, TypeToken<T> typeToken) {
        JolokiaQueryDto dto = new JolokiaQueryDto()
                .setType(JolokiaExecuteType.READ.getType())
                .setMbean(mbean)
                .setArguments(arguments);
        String response = getJolokiaResponse(config, dto);
        log.info("response: {}", response);
        return gson.fromJson(response, typeToken.getType());
    }


    public <T> T doSearchAttribute(ConnectConfig config, JolokiaExecuteType execType,
                                   String mbean, String attribute,
                                   TypeToken<T> typeToken) {
        JolokiaQueryDto dto = new JolokiaQueryDto()
                .setType(execType.getType())
                .setMbean(mbean)
                .setAttribute(attribute);
        String response = getJolokiaResponse(config, dto);
        log.info("response: {}", response);
        return gson.fromJson(response, typeToken.getType());
    }

    /**
     * 执行  不带参数的方法
     *
     * @param config   连接配置
     * @param execType 类型
     * @param mbean    mbean
     * @param method   方法
     * @param clazz    响应类型
     * @param <T>      响应类型
     * @return {@link ActiveMqJolokiaResponse<T>}  - 响应
     */
    public <T> Boolean doVoidMethod(ConnectConfig config, JolokiaExecuteType execType,
                                    String mbean, ActiveMqMethod method,
                                    Class<T> clazz) {
        JolokiaQueryDto dto = new JolokiaQueryDto()
                .setType(execType.getType())
                .setMbean(mbean)
                .setOperation(method.getName());
        String response = getJolokiaResponse(config, dto);
        log.info("response: {}", response);
        ActiveMqJolokiaResponse<T> responseDto = gson.fromJson(response, new TypeToken<ActiveMqJolokiaResponse<T>>() {
        }.getType());
        return responseDto.getStatus() == 200;
    }

}
