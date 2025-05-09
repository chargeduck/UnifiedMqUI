import request from '@/utils/request.js'

export function getQueueList(data) {
  return request({
    url: '/activemq/queues',
    method: 'post',
    data
  })
}
