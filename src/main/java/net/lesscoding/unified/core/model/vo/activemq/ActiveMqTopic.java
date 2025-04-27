package net.lesscoding.unified.core.model.vo.activemq;

import lombok.Data;

/**
 * @author eleven
 * @date 2025/4/24 9:01
 * @apiNote
 */
@Data
public class ActiveMqTopic {

    // 主题信息
    private String topicName;
    // 消费者数量
    private String topicConsumerCount;
    // 生产者数量
    private String topicProducerCount;
}
