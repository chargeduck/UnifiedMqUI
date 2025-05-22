package net.lesscoding.unified.rocketmq.entity;

import lombok.Data;

@Data
public class MqConfigRequest {
    private String nameSrvAddr; // 格式：ip:port（多个用分号分隔）
    private String topic;
    private String groupId;    // Producer/Consumer组名
}