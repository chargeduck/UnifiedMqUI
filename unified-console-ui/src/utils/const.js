// mq的类型数据
import logoActiveMQ from '@/assets/images/logo_activemq.png'
import logoRocketMQ from '@/assets/images/logo_rocketmq.svg'
import logoKafka from '@/assets/images/logo_kafka.png'
import logoRabbitMQ from '@/assets/images/logo_rabbitmq.svg'
import logoMqtt from '@/assets/images/logo_mqtt.svg'

export const mqTypeOptions = [
  { label: 'ActiveMQ', value: 0 },
  { label: 'RocketMQ', value: 1 },
  { label: 'Kafka', value: 2 },
  { label: 'RabbitMQ', value: 3 },
  { label: 'Mqtt', value: 4 }
]


export const logoList = [
  logoActiveMQ,
  logoRocketMQ,
  logoKafka,
  logoRabbitMQ,
  logoMqtt
]

export const navMenuList = [
  {
    index: '/dashboard',
    tooltip: 'Dashboard',
    textMenu: true
  },
  {
    index: '/activemq/index',
    logo: logoActiveMQ,
    tooltip: 'ActiveMQ',
    width: '100px',
    height: '50px',
    textMenu: true
  },
  {
    index: '/rocketmq/index',
    logo: logoRocketMQ,
    tooltip: 'RocketMQ',
    width: '100px',
    height: '50px',
    textMenu: true
  },
  {
    index: '/kafka/index',
    logo: logoKafka,
    tooltip: 'Kafka',
    width: '100px',
    height: '50px',
    textMenu: true
  },
  {
    index: '/rabbitmq/index',
    logo: logoRabbitMQ,
    tooltip: 'RabbitMQ',
    width: '100px',
    height: '50px',
    textMenu: true
  },
  {
    index: '/mqtt/index',
    logo: logoMqtt,
    tooltip: 'Mqtt',
    width: '100px',
    height: '50px',
    textMenu: true
  }
]

export const codePrefix = "```"
export const scheduledMd = `
# 如何开启ActiveMq Scheduled

1. 修改activemq.xml文件
${codePrefix}shell
vim activemq/conf/activemq.xml
${codePrefix}
2. 在 broker 标签中添加 schedulerSupport="true" 属性
${codePrefix}xml
<broker
  xmlns="http://activemq.apache.org/schema/core"
  brokerName="localhost"
  dataDirectory="\${activemq.data}"
  schedulerSupport="true">
</broker>
${codePrefix}
3. 重启ActiveMq
${codePrefix}shell
./bin/activemq restart
${codePrefix}
`
