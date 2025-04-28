import request from '@/utils/request'
export function  addMqConnect(data) {
  return request({
    url: '/mq/create',
    method: 'post',
    data
  })
}
