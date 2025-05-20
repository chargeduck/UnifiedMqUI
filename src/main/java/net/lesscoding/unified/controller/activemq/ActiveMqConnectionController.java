package net.lesscoding.unified.controller.activemq;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.core.model.Result;
import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.SearchResult;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.connection.ConnectionInfo;
import net.lesscoding.unified.service.activemq.ActiveMqConnectionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author eleven
 * @date 2025/5/19 15:34
 * @apiNote
 */
@RestController
@RequestMapping("/activemq/connection")
@RequiredArgsConstructor
public class ActiveMqConnectionController {
    private final ActiveMqConnectionService connectionService;

    /**
     * 连接列表
     *
     * @param dto
     */
    @PostMapping("/list")
    public Result<Page<ConnectionInfo>> connectionList(@RequestBody CommonQueryDto<String> dto) {
        return Result.success(connectionService.connectionList(dto));
    }

    @PostMapping("/consumers")
    public Result<Page> connectionConsumers(@RequestBody CommonQueryDto<List<SearchResult>> dto) {
        return Result.success(connectionService.connectionConsumers(dto));
    }
}
