package net.lesscoding.unified;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author eleven
 * @date 2025/4/21 9:46
 * @apiNote
 */
@SpringBootApplication
@MapperScan("net.lesscoding.unified.mapper")
public class UnifiedMqApp {
    public static void main(String[] args) {
        SpringApplication.run(UnifiedMqApp.class, args);
    }
}
