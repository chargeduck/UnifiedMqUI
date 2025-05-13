import request from '@/utils/request.js'

/**
 * 添加队列
 * @param data
 * @return {*}
 */
export function addQueue (data) {
  return request({
    url: '/activemq/addQueue',
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
    url: '/activemq/removeQueue',
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
export function getQueueMessageList (data) {
  return request({
    url: '/activemq/queue/messages',
    method: 'post',
    data
  })
}



/**
 * 暂停队列
 * @param data
 * @return {*}
 */
export function pauseQueue (data) {
  return request({
    url: '/activemq/queue/pause',
    method: 'post',
    data
  })
}

/**
 * 恢复队列
 * @param data
 * @return {*}
 */
export function resumeQueue (data) {
  return request({
    url: '/activemq/queue/resume',
    method: 'post',
    data
  })
}

/**
 * 清空队列
 * @param data
 * @return {*}
 */
export function purgeQueue (data) {
  return request({
    url: '/activemq/queue/purge',
    method: 'post',
    data
  })
}


/**
 * 发送消息到队列
 * @param data
 * @return {*}
 */
export function sendToQueue (data) {
  return request({
    url: '/activemq/queue/sendTo',
    method: 'post',
    data
  })
}

