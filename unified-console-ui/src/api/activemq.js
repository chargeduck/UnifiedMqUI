import request from '@/utils/request.js'

/**
 * 获取队列列表
 * @param data
 * @return {*}
 */
export function getQueueList(data) {
  return request({
    url: '/activemq/queues',
    method: 'post',
    data
  })
}

/**
 * 获取队列消息列表
 * @param data
 * @return {*}
 */
export function getQueueMessageList(data) {
  return request({
    url: '/activemq/queue/messages',
    method: 'post',
    data
  })
}
