import request from '@/utils/request.js'

export function jobScheduler (data) {
  return request({
    url: '/activemq/scheduled/jobScheduler',
    method: 'post',
    data
  })
}


export function allJobs (data) {
  return request({
    url: '/activemq/scheduled/allJobs',
    method: 'post',
    data
  })
}

export function removeJob (data) {
  return request({
    url: '/activemq/scheduled/removeJob',
    method: 'delete',
    data
  })
}
