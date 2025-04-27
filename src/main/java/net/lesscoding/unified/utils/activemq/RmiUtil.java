package net.lesscoding.unified.utils.activemq;

import cn.hutool.core.util.StrUtil;
import net.lesscoding.unified.core.model.vo.activemq.ActiveMqQueue;
import net.lesscoding.unified.core.model.vo.activemq.ActiveMqServerInfo;
import net.lesscoding.unified.core.model.vo.activemq.ActiveMqTopic;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author eleven
 * @date 2025/4/24 8:40
 * @apiNote
 */
public class RmiUtil {

    public static ActiveMqServerInfo serverInfo() {
        return serverInfo("localhost", "1099", "jmxrmi", "localhost");
    }

    /**
     * 获取 ActiveMQ 服务器信息
     *
     * @param host          主机名
     * @param port          端口号 默认 1099
     * @param contextPath   上下文路径 默认 /jmxrmi
     * @param brokerNameStr 代理名称 默认 localhost
     * @return {@link ActiveMqServerInfo}  ActiveMqServerInfo
     */
    public static ActiveMqServerInfo serverInfo(String host, String port, String contextPath, String brokerNameStr) {
        ActiveMqServerInfo serverInfo = new ActiveMqServerInfo();
        try {
            // 创建 JMX 服务 URL
            String format = StrUtil.format("service:jmx:rmi:///jndi/rmi://{}:{}/{}", host, port, contextPath);
            JMXServiceURL url = new JMXServiceURL(format);
            // 连接到 JMX 服务
            JMXConnector connector = JMXConnectorFactory.connect(url);
            MBeanServerConnection connection = connector.getMBeanServerConnection();

            // 获取 ActiveMQ 代理的 MBean 对象名称
            ObjectName brokerName = new ObjectName("org.apache.activemq:type=Broker,brokerName=" + brokerNameStr);

            // 获取代理基本信息
            serverInfo.setBrokerVersion(String.valueOf(connection.getAttribute(brokerName, "BrokerVersion")));
            serverInfo.setBrokerName(String.valueOf(connection.getAttribute(brokerName, "BrokerName")));

            // 获取连接信息
            serverInfo.setTotalConnectionsCount(String.valueOf(connection.getAttribute(brokerName, "TotalConnectionsCount")));

            // 获取目的地信息
            ObjectName queueQuery = new ObjectName("org.apache.activemq:type=Broker,brokerName=localhost,destinationType=Queue,destinationName=*");
            Set<ObjectName> queueNames = connection.queryNames(queueQuery, null);
            List<ActiveMqQueue> queueList = new ArrayList<>();
            ActiveMqQueue queue = null;
            // 队列信息
            for (ObjectName queueName : queueNames) {
                queue = new ActiveMqQueue();
                queue.setQueueName(String.valueOf(queueName.getKeyProperty("destinationName")));
                queue.setQueueSize(String.valueOf(connection.getAttribute(queueName, "QueueSize")));
                queue.setQueueConsumerCount(String.valueOf(connection.getAttribute(queueName, "ConsumerCount")));
                queue.setQueueProducerCount(String.valueOf(connection.getAttribute(queueName, "ProducerCount")));
                queueList.add(queue);
            }

            ObjectName topicQuery = new ObjectName("org.apache.activemq:type=Broker,brokerName=localhost,destinationType=Topic,destinationName=*");
            Set<ObjectName> topicNames = connection.queryNames(topicQuery, null);
            List<ActiveMqTopic> topicList = new ArrayList<>();
            ActiveMqTopic topic = null;
            // 主题信息
            for (ObjectName topicName : topicNames) {
                topic = new ActiveMqTopic();
                topic.setTopicName(String.valueOf(topicName.getKeyProperty("destinationName")));
                topic.setTopicConsumerCount(String.valueOf(connection.getAttribute(topicName, "ConsumerCount")));
                topic.setTopicProducerCount(String.valueOf(connection.getAttribute(topicName, "ProducerCount")));
                topicList.add(topic);
            }

            serverInfo.setQueueList(queueList);
            serverInfo.setTopicList(topicList);
            // 获取消息统计信息
            serverInfo.setTotalEnqueueCount(String.valueOf(connection.getAttribute(brokerName, "TotalEnqueueCount")));
            serverInfo.setTotalDequeueCount(String.valueOf(connection.getAttribute(brokerName, "TotalDequeueCount")));

            // 关闭连接
            connector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverInfo;
    }
}
