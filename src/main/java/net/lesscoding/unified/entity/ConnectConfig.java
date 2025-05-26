package net.lesscoding.unified.entity;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.lesscoding.unified.core.enums.MQType;
import net.lesscoding.unified.core.model.dto.ExtraInputPort;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

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
    private Integer port;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @TableField(exist = false)
    private MQType mqTypeEnum;

    @TableField(exist = false)
    public List<ExtraInputPort> inputPorts;

    @TableField(exist = false)
    public Integer extraPort;

    public Integer getExtraPort() {
        return CollUtil.isNotEmpty(inputPorts) ?
                inputPorts.stream().filter(item -> !item.getDefaultFlag()).findFirst().get().getInputPort() :
                port;
    }

    public MQType getMqTypeEnum() {
        return MQType.getByCode(mqType);
    }
}
