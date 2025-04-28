package net.lesscoding.unified.controller;

import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.core.model.Result;
import net.lesscoding.unified.entity.ConnectConfig;
import net.lesscoding.unified.service.MqConnectManagerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result createMqConnect(@RequestBody ConnectConfig connectConfig) {
        connectConfig = mqConnectManagerService.createMqConnect(connectConfig);
        return Result.success(connectConfig);
    }

    @PostMapping("/list")
    public Result mqList() {
        return Result.success(mqConnectManagerService.mqList());
    }
}
