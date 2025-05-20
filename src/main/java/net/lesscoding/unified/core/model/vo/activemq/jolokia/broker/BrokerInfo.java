package net.lesscoding.unified.core.model.vo.activemq.jolokia.broker;

import lombok.Data;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.SearchResult;

import java.util.List;

/**
 * @author eleven
 * @date 2025/4/28 14:39
 * @apiNote
 */
@Data
public class BrokerInfo {
    private boolean StatisticsEnabled;
    private int TotalConnectionsCount;
    private int TotalMessageCount;
    private int TempPercentUsage;
    private int MemoryPercentUsage;
    /**
     * openwire : tcp://eleven:61616?maximumConnections=1000&wireFormat.maxFrameSize=104857600
     * amqp : amqp://eleven:5672?maximumConnections=1000&wireFormat.maxFrameSize=104857600
     * mqtt : mqtt://eleven:1883?maximumConnections=1000&wireFormat.maxFrameSize=104857600
     * stomp : stomp://eleven:61613?maximumConnections=1000&wireFormat.maxFrameSize=104857600
     * ws : ws://eleven:61614?maximumConnections=1000&wireFormat.maxFrameSize=104857600
     */

    private BrokerTransportConnectors BrokerTransportConnectors;
    private long StoreLimit;
    private int TotalProducerCount;
    private int CurrentConnectionsCount;
    private Object JMSJobScheduler;
    private String VMURL;
    private int UptimeMillis;
    private int TotalDequeueCount;
    private int JobSchedulerStorePercentUsage;
    private String Uptime;
    private int AverageMessageSize;
    private String BrokerId;
    private String DataDirectory;
    private boolean Persistent;
    private String BrokerVersion;
    private String BrokerName;
    private int MemoryLimit;
    private int MinMessageSize;
    private boolean Slave;
    private int TotalEnqueueCount;
    private long TempLimit;
    private int JobSchedulerStoreLimit;
    private int TotalConsumerCount;
    private int StorePercentUsage;
    private int MaxMessageSize;
    private List<?> TemporaryQueueSubscribers;
    private List<?> InactiveDurableTopicSubscribers;
    private List<?> TopicProducers;
    private List<?> QueueProducers;
    private List<?> TemporaryTopicProducers;
    private List<?> TemporaryQueueProducers;
    /**
     * objectName : org.apache.activemq:brokerName=localhost,destinationName=ActiveMQ.Advisory.MasterBroker,destinationType=Topic,type=Broker
     */

    private List<SearchResult> Topics;
    private List<?> DurableTopicSubscribers;
    private List<?> QueueSubscribers;
    private List<?> TopicSubscribers;
    private List<?> TemporaryQueues;
    private List<?> DynamicDestinationProducers;
    private List<?> TemporaryTopicSubscribers;
    private List<?> TemporaryTopics;
    /**
     * objectName : org.apache.activemq:brokerName=localhost,destinationName=TestQueue,destinationType=Queue,type=Broker
     */

    private List<SearchResult> Queues;


}
