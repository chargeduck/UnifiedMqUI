package net.lesscoding.unified.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import lombok.Data;
import net.lesscoding.unified.core.enums.MQType;

import java.time.LocalDateTime;

/**
 * @author eleven
 * @date 2025/4/23 15:10
 * @apiNote
 */
@Data
public class ConnectConfig {
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 主机
    private String host;
    // 端口
    private String port;
    // Mq类型
    private Integer mqType;

    // 是否为集群
    private Boolean groupType;
    // 集群地址
    private String groupUrl;
    // 标题
    private String title;

    private String username;

    private String password;

    private String version;

    private String extra;

    private String brokerName;

    private String brokerId;

    private String upTime;

    private Boolean activeFlag;

    private LocalDateTime createTime;

    @TableField(exist = false)
    private MQType mqTypeEnum;

    @TableField(exist = false)
    private PageDTO page;

    public MQType getMqTypeEnum() {
        return MQType.getByCode(mqType);
    }
}
