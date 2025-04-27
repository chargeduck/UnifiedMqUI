package net.lesscoding.unified.core.model.vo.activemq;

import lombok.Data;

import java.util.List;

/**
 * @author eleven
 * @date 2025/4/24 8:41
 * @apiNote
 */
@Data
public class ActiveMqServerInfo {
    // 版本号
    private String brokerVersion;
    // 代理名称
    private String brokerName;
    // 连接数
    private String totalConnectionsCount;
    // 消息入队总数
    private String totalEnqueueCount;
    // 消息出队总数
    private String totalDequeueCount;

    private List<ActiveMqQueue> queueList;
    private List<ActiveMqTopic> topicList;

}
