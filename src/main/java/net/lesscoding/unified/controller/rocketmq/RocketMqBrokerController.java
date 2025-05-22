package net.lesscoding.unified.controller.rocketmq;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eleven
 * @date 2025/5/22 10:51
 * @apiNote
 */
@RestController
@RequestMapping("/rocketmq/broker")
@RequiredArgsConstructor
public class RocketMqBrokerController {

    @PostMapping("/config")
    public String brokerConfig() {
        return "";
    }
}
