// mq的类型数据
import logoActiveMQ from '@/assets/logo_activemq.png'
import logoRocketMQ from '@/assets/logo_rocketmq.svg'
import logoKafka from '@/assets/logo_kafka.png'
import logoRabbitMQ from '@/assets/logo_rabbitmq.svg'
import logoMqtt from '@/assets/logo_mqtt.svg'

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
    height: '50px'
  },
  {
    index: '/rocketmq/index',
    logo: logoRocketMQ,
    tooltip: 'RocketMQ',
    width: '100px',
    height: '50px'
  },
  {
    index: '/kafka/index',
    logo: logoKafka,
    tooltip: 'Kafka',
    width: '100px',
    height: '50px'
  },
  {
    index: '/rabbitmq/index',
    logo: logoRabbitMQ,
    tooltip: 'RabbitMQ',
    width: '100px',
    height: '50px'
  },
  {
    index: '/mqtt/index',
    logo: logoMqtt,
    tooltip: 'Mqtt',
    width: '100px',
    height: '50px'
  }
]
