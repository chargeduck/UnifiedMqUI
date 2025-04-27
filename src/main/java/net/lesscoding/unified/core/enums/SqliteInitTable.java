package net.lesscoding.unified.core.enums;

import lombok.Getter;

/**
 * @author eleven
 * @date 2025/4/27 11:01
 * @apiNote
 */
@Getter
public enum SqliteInitTable {
    // MQ配置表
    CONNECT_CONFIG("connect_config", "sql/sqlite/connect_config.sql");

    private final String tbName;

    private final String path;

    SqliteInitTable(String tbName, String path) {
        this.tbName = tbName;
        this.path = path;
    }
}
