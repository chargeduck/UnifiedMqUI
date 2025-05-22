# 如何开启ActiveMq Scheduled

1. 修改activemq.xml文件
```shell
vim activemq/conf/activemq.xml
```
2. 在 broker 标签中添加 schedulerSupport="true" 属性

```xml
<broker
        xmlns="http://activemq.apache.org/schema/core"
        brokerName="localhost"
        dataDirectory="\${activemq.data}"
        schedulerSupport="true">
</broker>
```

3. 重启ActiveMq
```shell
./bin/activemq restart
```