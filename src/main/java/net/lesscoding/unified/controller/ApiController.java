package net.lesscoding.unified.controller;

import cn.hutool.core.util.IdUtil;
import net.lesscoding.unified.core.model.Result;
import net.lesscoding.unified.core.model.dto.LoginDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eleven
 * @date 2025/4/24 17:18
 * @apiNote
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/login")
    public Result login(@RequestBody LoginDto dto){
        return Result.success("Bearer " + IdUtil.fastSimpleUUID());
    }

    @PostMapping("/reg")
    public Result reg(){
        return Result.success();
    }
}
