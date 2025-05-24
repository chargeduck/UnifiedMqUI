package net.lesscoding.unified.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author eleven
 * @date 2025/5/23 14:17
 * @apiNote
 */
@Data
public class MqPortDict {
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 消息队列类型
     */
    private Integer mqType;

    /**
     * 是否默认
     */

    private Boolean defaultFlag;
    /**
     * 端口 label
     */

    private String portLabel;
    /**
     * 默认端口
     */

    private Integer defaultPort;
}
