package net.lesscoding.unified.core.model.vo.activemq;

import lombok.Data;

/**
 * @author eleven
 * @date 2025/4/24 9:00
 * @apiNote
 */
@Data
public class ActiveMqQueue {
    // 队列信息
    private String queueName;
    // 队列消费数量
    private String queueSize;
    // 队列消费者数量
    private String queueConsumerCount;
    // 生产者数量
    private String queueProducerCount;
}
