import request from '@/utils/request.js'


export function allMqPort() {
  return request({
    url: '/mqPortDict/all',
    method: 'get'
  })
}
