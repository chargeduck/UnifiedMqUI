import request from '@/utils/request.js'

/**
 * 获取队列消息列表
 * @param data
 * @return {*}
 */
export function getQueueMessageList (data) {
  return request({
    url: '/activemq/queue/messagesPage',
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
    url: '/activemq/queue/sendMessage',
    method: 'post',
    data
  })
}

/**
 * 移除队列消息
 * @param data
 * @return {*}
 */
export function removeMessage (data) {
  return request({
    url: '/activemq/queue/removeMessage',
    method: 'delete',
    data
  })
}
