package net.lesscoding.unified.core.model.dto.activemq;

import lombok.Data;

/**
 * @author eleven
 * @date 2025/5/19 10:47
 * @apiNote
 */
@Data
public class DurableSubscribeDto {
    private String clientId;
    private String subscriptionName;
    private String topicName;
    private String selector;
}
