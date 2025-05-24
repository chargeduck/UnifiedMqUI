package net.lesscoding.unified.core.enums;

import lombok.Getter;

/**
 * @author eleven
 * @date 2024/8/15 10:44
 * @apiNote
 */
@Getter
public enum DerbyInitTable {
    // MQ配置表
    CONNECT_CONFIG("connect_config", "sql/derby/connect_config.sql"),
    MQ_PORT_DICT("mq_port_dict", "sql/derby/mq_port_dict.sql"),
    ;

    private final String tbName;

    private final String path;

    DerbyInitTable(String tbName, String path) {
        this.tbName = tbName;
        this.path = path;
    }
}
