package net.lesscoding.unified.core.model.vo.activemq.jolokia;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author eleven
 * @date 2025/5/20 16:49
 * @apiNote
 */
@Data
public class SchedulerInfo {

    private String next;
    private String cronEntry;
    private String jobId;
    private Integer period;
    private Integer delay;
    private Integer repeat;

    private String start;

    public LocalDateTime getStartTime() {
        if (StrUtil.isNotBlank(start)) {
            return LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        return LocalDateTime.now();
    }
}
