import request from '@/utils/request.js'

export function fetchSubscriberPage (data) {
  return request({
    url: '/activemq/subscriber/conditionPage',
    method: 'post',
    data
  })
}
