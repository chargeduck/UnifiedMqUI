package net.lesscoding.unified.core.model.dto.activemq;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import lombok.Data;
import net.lesscoding.unified.entity.ConnectConfig;

/**
 * @author eleven
 * @date 2025/5/9 16:22
 * @apiNote
 */
@Data
public class QueueQueryDto<T> {

    private ConnectConfig config;

    private T params;

    private PageDTO<T> page;
}
