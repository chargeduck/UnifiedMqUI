package net.lesscoding.unified.core.model.dto.activemq;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author eleven
 * @date 2025/4/24 10:22
 * @apiNote
 */
@Data
@Accessors(chain = true)
public class JolokiaQueryDto {
    private String type;

    private String mbean;

    private String attribute;

    private String operation;

    private Object arguments;

    private Object signature;
}
