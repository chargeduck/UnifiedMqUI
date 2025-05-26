import request from '@/utils/request'

export function addMqConnect (data) {
  return request({
    url: '/mq/create',
    method: 'post',
    data
  })
}

export function fetchConnectList (data) {
  return request({
    url: '/mq/list',
    method: 'post',
    data
  })
}

export function getConnectById (id) {
  return request({
    url: `/mq/${ id }`,
    method: 'get'
  })
}

export function delById (id) {
  return request({
    url: `/mq/${ id }`,
    method: 'delete'
  })
}

export function helpMd (data) {
  return request({
    url: '/mq/help',
    method: 'post',
    data
  })
}
