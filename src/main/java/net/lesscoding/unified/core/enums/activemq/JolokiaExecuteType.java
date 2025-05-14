package net.lesscoding.unified.core.enums.activemq;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author eleven
 * @date 2025/5/14 11:03
 * @apiNote
 */
@Getter
@AllArgsConstructor
public enum JolokiaExecuteType {
    // 执行操作
    EXEC("exec"),
    // 读取MBEAN属性
    READ("read"),
    // 写入MBEAN属性
    WRITE("write"),
    // 搜索MBEAN
    SEARCH("search"),
    // 列出MBEAN
    LIST("list");
    private final String type;
}
