package net.lesscoding.unified.core.enums;

import lombok.Getter;

/**
 * @author eleven
 * @date 2024/8/15 10:44
 * @apiNote
 */
@Getter
public enum InitTable {
    // MQ配置表
    CONNECT_CONFIG("connect_config", "sql/connect_config.sql");

    private final String tbName;

    private final String path;

    InitTable(String tbName, String path) {
        this.tbName = tbName;
        this.path = path;
    }
}
