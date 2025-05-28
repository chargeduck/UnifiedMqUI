package net.lesscoding.unified.core.model.vo;

import lombok.Data;

/**
 * @author eleven
 * @date 2025/5/28 16:58
 * @apiNote
 */
@Data
public class ExtraInputPortVo {
    private Integer id;

    private String portLabel;

    private Integer defaultPort;

    private Boolean defaultFlag;

    private Integer inputPort;

    private String desc;
}
