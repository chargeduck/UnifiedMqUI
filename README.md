# unifyMqUi
A unified message queue UI page, which is built by using springboot vue derby, aims to manage such as ActiveMQ Rocket MQ Rabbit MQ Kafka MQTT through a project, and can provide exe or .bat.sh batch files. In the future, it is considered to realize the above functions through electron.

# ActiveMq

## 1. 使用 JMX RMI

## 2. 使用 jolokia REST API
**参考链接**
1. [ActiveMq 与 RestAPI 实践](https://blog.csdn.net/iteye_19607/article/details/82569577)
2. [ActiveMQ使用RESTful API发送消息](https://blog.csdn.net/u012383839/article/details/72875161)
3. [Rest Api Doc](https://activemq.apache.org/components/classic/documentation/rest)

> 通过 jolokia REST API 链接 activemq的时候, 所有的url都是一样的，针对不同的接口，传入不同的json参数即可

```java
 private void jolokiaTest(ActiveMqJolokiaDto dto){
    // ActiveMQ Jolokia REST API 端点
    String url = "http://127.0.0.1:8161/api/jolokia/";
    // 发送 POST 请求
    HttpResponse response = HttpRequest.post(url)
            .header("Origin", "*")
            .basicAuth("admin", "admin")
            .body(gson.toJson(dto))
            .execute();

    // 检查响应状态码
    if (response.isOk()) {
        // 打印响应结果
        System.out.println(response.body());
    } else {
        System.err.println("请求失败，状态码: " + response.getStatus());
    }
}
```

### 1. 获取 MBEAN 信息
- 请求参数
> `attribute`可为空，为空则获取所有的属性信息
```json
{
    "type": "read",
    "mbean": "org.apache.activemq:type=Broker,brokerName=localhost",
    "attribute": "BrokerVersion"
}
```
- 响应结果
```json
{
  "request": {
    "mbean": "org.apache.activemq:brokerName=localhost,type=Broker",
    "type": "read"
  },
  "value": {
    "StatisticsEnabled": true,
    "TemporaryQueueSubscribers": [],
    "TempPercentUsage": 0,
    "TotalMessageCount": 0,
    "TotalConnectionsCount": 0,
    "MemoryPercentUsage": 0,
    "TransportConnectors": {
      "openwire": "tcp://eleven:61616?maximumConnections=1000&wireFormat.maxFrameSize=104857600",
      "amqp": "amqp://eleven:5672?maximumConnections=1000&wireFormat.maxFrameSize=104857600",
      "mqtt": "mqtt://eleven:1883?maximumConnections=1000&wireFormat.maxFrameSize=104857600",
      "stomp": "stomp://eleven:61613?maximumConnections=1000&wireFormat.maxFrameSize=104857600",
      "ws": "ws://eleven:61614?maximumConnections=1000&wireFormat.maxFrameSize=104857600"
    },
    "InactiveDurableTopicSubscribers": [],
    "StoreLimit": 107374182400,
    "TotalProducerCount": 0,
    "TopicProducers": [],
    "CurrentConnectionsCount": 0,
    "QueueProducers": [],
    "JMSJobScheduler": null,
    "VMURL": "vm://localhost",
    "UptimeMillis": 4540725,
    "TemporaryQueueProducers": [],
    "TemporaryTopicProducers": [],
    "TotalDequeueCount": 0,
    "Topics": [
      {
        "objectName": "org.apache.activemq:brokerName=localhost,destinationName=ActiveMQ.Advisory.MasterBroker,destinationType=Topic,type=Broker"
      },
      {
        "objectName": "org.apache.activemq:brokerName=localhost,destinationName=ActiveMQ.Advisory.Queue,destinationType=Topic,type=Broker"
      }
    ],
    "DurableTopicSubscribers": [],
    "JobSchedulerStorePercentUsage": 0,
    "Uptime": "1 hour 15 minutes",
    "QueueSubscribers": [],
    "AverageMessageSize": 1024,
    "BrokerId": "ID:eleven-56408-1745454689768-0:1",
    "DataDirectory": "E:\\ide\\apache-activemq-5.16.8\\data",
    "Persistent": true,
    "TopicSubscribers": [],
    "TemporaryQueues": [],
    "BrokerVersion": "5.16.8",
    "BrokerName": "localhost",
    "MemoryLimit": 751619277,
    "MinMessageSize": 1024,
    "Slave": false,
    "DynamicDestinationProducers": [],
    "TotalEnqueueCount": 2,
    "TempLimit": 53687091200,
    "TemporaryTopicSubscribers": [],
    "TemporaryTopics": [],
    "JobSchedulerStoreLimit": 0,
    "Queues": [
      {
        "objectName": "org.apache.activemq:brokerName=localhost,destinationName=1234,destinationType=Queue,type=Broker"
      }
    ],
    "TotalConsumerCount": 0,
    "StorePercentUsage": 0,
    "MaxMessageSize": 1024
  },
  "timestamp": 1745459230,
  "status": 200
}
```

###  2. 