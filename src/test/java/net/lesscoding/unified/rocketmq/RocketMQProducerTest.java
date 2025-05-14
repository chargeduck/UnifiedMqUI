package net.lesscoding.unified.rocketmq;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;

public class RocketMQProducerTest {
    static String host = "localhost";

    @Test
    public void SyncProducer() throws Exception {
        // 初始化一个producer并设置Producer group name
        DefaultMQProducer producer = new DefaultMQProducer("unifiedMqTestGroup"); //（1）
        // 设置NameServer地址
        producer.setNamesrvAddr(host + ":9876");  //（2）
        // 启动producer
        producer.start();
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000); // 一秒发一次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 创建一条消息，并指定topic、tag、body等信息，tag可以理解成标签，对消息进行再归类，RocketMQ可以在消费端对tag进行过滤
            Message msg = new Message("broker-a",
                    "TagA",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );  //（3）
            // 利用producer进行发送，并同步等待发送结果
            SendResult sendResult = producer.send(msg);   //（4）
            System.out.printf("%s%n", sendResult);
        }
        // 一旦producer不再使用，关闭producer
        producer.shutdown();
    }
}
