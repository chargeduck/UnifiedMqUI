package net.lesscoding.unified.enums;

import lombok.Getter;

/**
 * @author eleven
 * @date 2024/8/15 10:44
 * @apiNote
 */
@Getter
public enum InitTable {
    TB_UTILS_USER("tb_util_user", "sql/tb_util_user.sql");

    private String tbName;

    private String path;

    InitTable(String tbName, String path) {
        this.tbName = tbName;
        this.path = path;
    }
}
