package net.lesscoding.unified.core.adapter;

import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.enums.MQType;
import org.springframework.stereotype.Component;

/**
 * @author eleven
 * @date 2025/4/23 15:25
 * @apiNote
 */
@Component
@RequiredArgsConstructor
public class MqAdapterFactory {
    private final KafkaAdapter kafkaAdapter;
    private final MqttMqAdapter mqttMqAdapter;
    private final RocketMqAdapter rocketMqAdapter;
    private final ActiveMqAdapter activeMqAdapter;
    private final RabbitMqAdapter rabbitMqAdapter;

    /**
     * 获取mqAdapter
     *
     * @param mqType mq类型
     * @return {@link  MqAdapter} mqAdapter
     */
    public MqAdapter getMqAdapter(MQType mqType) {
        MqAdapter mqAdapter = null;
        switch (mqType) {
            case MQTT:
                mqAdapter = mqttMqAdapter;
                break;
            case ROCKET_MQ:
                mqAdapter = rocketMqAdapter;
                break;
            case KAFKA:
                mqAdapter = kafkaAdapter;
                break;
            case ACTIVE_MQ:
                mqAdapter = activeMqAdapter;
                break;
            case RABBIT_MQ:
                mqAdapter = rabbitMqAdapter;
                break;
            default:
                throw new RuntimeException("mqType is not support");
        }
        return mqAdapter;
    }
}
