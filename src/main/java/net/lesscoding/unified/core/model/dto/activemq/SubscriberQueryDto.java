package net.lesscoding.unified.core.model.dto.activemq;

import lombok.Data;

/**
 * @author eleven
 * @date 2025/5/15 17:37
 * @apiNote
 */
@Data
public class SubscriberQueryDto {
    private String name;

    private String subscriptionName;

    private Boolean active;

    private Boolean durable;
}
