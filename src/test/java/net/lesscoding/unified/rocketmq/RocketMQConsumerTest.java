package net.lesscoding.unified.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.junit.Test;

import java.util.List;

public class RocketMQConsumerTest {

    static String host = "localhost";

    @Test
    public void testConsumer() throws MQClientException {
        // 1. 初始化消费者，指定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("unifiedMqTestGroup");

        // 2. 设置NameServer地址
        consumer.setNamesrvAddr(host + ":9876");

        // 3. 设置消费模式：集群模式（默认）或广播模式
        consumer.setMessageModel(MessageModel.CLUSTERING);

        // 4. 订阅主题和标签（Tag），"*"表示订阅所有Tag
        consumer.subscribe("broker-a", "TagA || TagB"); // 可以订阅多个Tag，用"||"分隔

        // 5. 注册消息监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messages,
                                                            ConsumeConcurrentlyContext context) {
                // 处理消息
                for (MessageExt message : messages) {
                    try {
                        String topic = message.getTopic();
                        String tags = message.getTags();
                        String body = new String(message.getBody(), "UTF-8");

                        System.out.printf("收到消息: topic=%s, tags=%s, body=%s %n",
                                topic, tags, body);
                    } catch (Exception e) {
                        e.printStackTrace();
                        // 稍后重试
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }

                // 消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // 6. 启动消费者
        consumer.start();
        System.out.println("消费者已启动，等待消息...");

        // 保持消费者运行（测试时可以使用）
        try {
            Thread.sleep(60 * 1000); // 运行60秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 7. 关闭消费者（实际应用中通常不需要关闭）
        consumer.shutdown();
    }

    /**
     * 定义方法
     *
     * @date 2025/5/21 16:13
     */
    @Test
    public void testConsumer2() {

    }
}