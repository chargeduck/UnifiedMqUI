package net.lesscoding.unified.core.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * @author eleven
 * @date 2025/5/26 10:24
 * @apiNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtraInputPort {
    private Integer id;
    private Boolean defaultFlag;
    private Integer defaultPort;
    private Integer inputPort;

    public Integer getInputPort() {
        return Optional.ofNullable(inputPort).orElse(defaultPort);
    }
}
