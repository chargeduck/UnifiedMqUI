package net.lesscoding.unified.core.model.dto.activemq;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author eleven
 * @date 2025/5/14 8:23
 * @apiNote
 */
@Data
public class MessageQueryDto {

    private String name;

    private String text;

    private String messageId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
