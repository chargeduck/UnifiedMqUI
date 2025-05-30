package net.lesscoding.unified.core.model.vo.activemq.jolokia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.lesscoding.unified.core.model.vo.activemq.jolokia.broker.QueryRequest;

/**
 * @author eleven
 * @date 2025/4/28 14:31
 * @apiNote
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveMqJolokiaResponse<T> {

    /**
     * mbean : org.apache.activemq:brokerName=localhost,type=Broker
     * type : read
     */

    private QueryRequest request;

    private T value;
    /**
     * request : {"mbean":"org.apache.activemq:brokerName=localhost,type=Broker","type":"read"}
     * value : {"StatisticsEnabled":true,"TotalConnectionsCount":0,"TotalMessageCount":0,"TempPercentUsage":0,"TemporaryQueueSubscribers":[],"MemoryPercentUsage":0,"BrokerTransportConnectors":{"openwire":"tcp://eleven:61616?maximumConnections=1000&wireFormat.maxFrameSize=104857600","amqp":"amqp://eleven:5672?maximumConnections=1000&wireFormat.maxFrameSize=104857600","mqtt":"mqtt://eleven:1883?maximumConnections=1000&wireFormat.maxFrameSize=104857600","stomp":"stomp://eleven:61613?maximumConnections=1000&wireFormat.maxFrameSize=104857600","ws":"ws://eleven:61614?maximumConnections=1000&wireFormat.maxFrameSize=104857600"},"InactiveDurableTopicSubscribers":[],"StoreLimit":107374182400,"TotalProducerCount":0,"CurrentConnectionsCount":0,"TopicProducers":[],"QueueProducers":[],"JMSJobScheduler":null,"VMURL":"vm://localhost","UptimeMillis":491683,"TemporaryTopicProducers":[],"TemporaryQueueProducers":[],"TotalDequeueCount":0,"JobSchedulerStorePercentUsage":0,"Topics":[{"objectName":"org.apache.activemq:brokerName=localhost,destinationName=ActiveMQ.Advisory.MasterBroker,destinationType=Topic,type=Broker"},{"objectName":"org.apache.activemq:brokerName=localhost,destinationName=ActiveMQ.Advisory.Queue,destinationType=Topic,type=Broker"}],"DurableTopicSubscribers":[],"Uptime":"8 minutes","QueueSubscribers":[],"AverageMessageSize":1024,"BrokerId":"ID:eleven-58093-1745821364145-0:1","DataDirectory":"E:\\ide\\apache-activemq-5.16.8\\data","Persistent":true,"TopicSubscribers":[],"TemporaryQueues":[],"BrokerVersion":"5.16.8","BrokerName":"localhost","MemoryLimit":751619277,"MinMessageSize":1024,"Slave":false,"TotalEnqueueCount":2,"DynamicDestinationProducers":[],"TempLimit":53687091200,"TemporaryTopicSubscribers":[],"TemporaryTopics":[],"JobSchedulerStoreLimit":0,"TotalConsumerCount":0,"StorePercentUsage":0,"Queues":[{"objectName":"org.apache.activemq:brokerName=localhost,destinationName=TestQueue,destinationType=Queue,type=Broker"}],"MaxMessageSize":1024}
     * timestamp : 1745821855
     * status : 200
     */
    private int timestamp;
    private int status;


}

