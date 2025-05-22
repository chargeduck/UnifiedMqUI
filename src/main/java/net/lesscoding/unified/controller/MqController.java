package net.lesscoding.unified.controller;

import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.core.model.Result;
import net.lesscoding.unified.core.model.dto.CommonQueryDto;
import net.lesscoding.unified.entity.ConnectConfig;
import net.lesscoding.unified.entity.HelpMarkdown;
import net.lesscoding.unified.service.MqConnectManagerService;
import org.springframework.web.bind.annotation.*;

/**
 * @author eleven
 * @date 2025/4/23 15:57
 * @apiNote
 */
@RestController
@RequestMapping("/mq")
@RequiredArgsConstructor
public class MqController {
    private final MqConnectManagerService mqConnectManagerService;
    @PostMapping("/create")
    public Result createMqConnect(@RequestBody CommonQueryDto<ConnectConfig> dto) {
        ConnectConfig connectConfig = dto.getParams();
        connectConfig = mqConnectManagerService.createMqConnect(connectConfig);
        return Result.success(connectConfig);
    }

    @PostMapping("/list")
    public Result mqList(@RequestBody CommonQueryDto<ConnectConfig> dto) {
        return Result.success(mqConnectManagerService.mqList(dto));
    }

    @GetMapping("/{id}")
    public Result getMqConnect(@PathVariable Integer id) {
        return Result.success(mqConnectManagerService.getMqConnect(id));
    }

    @DeleteMapping("/{id}")
    public Result deleteMqConnect(@PathVariable Integer id) {
        return Result.success(mqConnectManagerService.delById(id));
    }

    @PostMapping("/help")
    public Result mqHelp(@RequestBody CommonQueryDto<HelpMarkdown> dto) {
        return Result.success(mqConnectManagerService.mqHelp(dto));
    }

}
