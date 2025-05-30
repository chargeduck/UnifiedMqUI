// mq的类型数据
import logoActiveMQ from '@/assets/images/logo_activemq.png'
import logoRocketMQ from '@/assets/images/logo_rocketmq.svg'
import logoKafka from '@/assets/images/logo_kafka.png'
import logoRabbitMQ from '@/assets/images/logo_rabbitmq.svg'
import logoMqtt from '@/assets/images/logo_mqtt.svg'

export const mqTypeOptions = [
  { label: 'ActiveMQ', value: 0, routerPush: 'activeMQ' },
  { label: 'RocketMQ', value: 1, routerPush: 'rocketMQ' },
  { label: 'Kafka', value: 2, routerPush: 'kafka' },
  { label: 'RabbitMQ', value: 3, routerPush: 'rabbitMQ' },
  { label: 'Mqtt', value: 4, routerPush: 'mqtt' }
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

export const codePrefix = '```'
