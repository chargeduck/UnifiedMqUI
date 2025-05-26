package net.lesscoding.unified.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author eleven
 * @date 2025/5/23 14:22
 * @apiNote
 */
@Data
public class ConnectPort {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer portDictId;

    private Integer connectId;

    private Integer inputPort;
}
