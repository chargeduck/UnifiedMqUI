import request from '@/utils/request.js'

/**
 * 添加队列
 * @param data
 * @return {*}
 */
export function addQueue (data) {
  return request({
    url: '/activemq/broker/addQueue',
    method: 'post',
    data
  })
}

/**
 * 删除队列
 * @param data
 * @return {*}
 */
export function removeQueue (data) {
  return request({
    url: '/activemq/broker/removeQueue',
    method: 'delete',
    data
  })
}

/**
 * 获取队列列表
 * @param data
 * @return {*}
 */
export function getQueueList (data) {
  return request({
    url: '/activemq/broker/queues',
    method: 'post',
    data
  })
}

/**
 * 获取主题列表
 * @param data
 * @return {*}
 */
export function getTopicList (data) {
  return request({
    url: '/activemq/broker/topics',
    method: 'post',
    data
  })
}

export function addTopic (data) {
  return request({
    url: '/activemq/broker/addTopic',
    method: 'post',
    data
  })
}

export function removeTopic (data) {
  return request({
    url: '/activemq/broker/removeTopic',
    method: 'delete',
    data
  })
}
