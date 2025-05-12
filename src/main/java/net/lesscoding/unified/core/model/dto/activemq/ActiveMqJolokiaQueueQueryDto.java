package net.lesscoding.unified.core.model.dto.activemq;

import lombok.Data;
import net.lesscoding.unified.entity.ConnectConfig;

/**
 * @author eleven
 * @date 2025/5/9 16:22
 * @apiNote
 */
@Data
public class ActiveMqJolokiaQueueQueryDto {

    private ConnectConfig config;

    private String queueName;

    private String topicName;
}
