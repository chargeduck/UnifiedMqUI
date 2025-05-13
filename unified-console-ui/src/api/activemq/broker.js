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
