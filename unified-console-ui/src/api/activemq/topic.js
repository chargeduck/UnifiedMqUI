import request from '@/utils/request.js'

/**
 * 向 Topic 发送消息
 * @param data
 * @return {*}
 */
export function sendToTopic (data) {
  return request({
    url: '/activemq/topic/sendMessage',
    method: 'post',
    data
  })
}

export function activeSubscribers (data) {
  return request({
    url: '/activemq/topic/activeSubscribers',
    method: 'post',
    data
  })
}
