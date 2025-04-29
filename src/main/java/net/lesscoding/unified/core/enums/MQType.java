package net.lesscoding.unified.core.enums;

/**
 * @author eleven
 * @date 2025/4/23 15:08
 * @apiNote
 */
public enum MQType {
    ACTIVE_MQ,
    ROCKET_MQ,
    KAFKA,
    RABBIT_MQ,
    MQTT,
    UNDEFINED;

    public static MQType getByCode(Integer mqType) {
        if (mqType == null) {
            return UNDEFINED;
        }
        for (MQType value : values()) {
            if (value.ordinal() == mqType) {
                return value;
            }
        }
        return UNDEFINED;
    }
}
