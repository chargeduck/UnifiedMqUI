import request from '@/utils/request.js'

/**
 * 连接列表
 * @param data
 * @return {*}
 */
export function fetchConnectList (data) {
  return request({
    url: '/activemq/connection/list',
    method: 'post',
    data
  })
}

export function fetchNetworkConnectorList (data) {
  return request({
    url: '/activemq/connection/networkConnectorList',
    method: 'post',
    data
  })
}

export function fetchNetworkBridgeList (data) {
  return request({
    url: '/activemq/connection/networkBridges',
    method: 'post',
    data
  })
}

/**
 *  连接消费者
 * @param data
 * @return {*}
 */
export function connectionConsumers (data) {
  return request({
    url: '/activemq/connection/consumers',
    method: 'post',
    data
  })
}
