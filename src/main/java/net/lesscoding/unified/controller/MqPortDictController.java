package net.lesscoding.unified.controller;

import lombok.RequiredArgsConstructor;
import net.lesscoding.unified.core.model.Result;
import net.lesscoding.unified.service.MqPortDictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eleven
 * @date 2025/5/24 15:14
 * @apiNote
 */
@RestController
@RequestMapping("/mqPortDict")
@RequiredArgsConstructor
public class MqPortDictController {

    private final MqPortDictService mqPortDictService;

    @GetMapping("/all")
    public Result all() {
        return Result.success(mqPortDictService.all());
    }
}
