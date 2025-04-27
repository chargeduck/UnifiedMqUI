import request from '@/utils/request'

// 获取文章分类
export function artGetChannelsService () {
  return request({
    url: '/my/cate/list',
    method: 'get'
  })
}

// 添加文章分类
export function artAddChannelService (data) {
  return request({
    url: '/my/cate/add',
    method: 'post',
    data
  })
}

// 编辑文章分类
export function artEditChannelService (data) {
  return request({
    url: '/my/cate/info',
    method: 'put',
    data
  })
}

// 删除文章分类
export function artDelChannelService (params) {
  return request({
    url: '/my/cate/del',
    method: 'delete',
    params
  })
}

export function artGetListService (params) {
  return request({
    url: '/my/article/list',
    method: 'get',
    params
  })
}

export function artDelService (params) {
  return request({
    url: '/my/article/del',
    method: 'delete',
    params
  })
}

export function artPublishService (data) {
  return request({
    url: '/my/article/add',
    method: 'post',
    data
  })
}

export function artGetDetailService (params) {
  return request({
    url: '/my/article/info',
    method: 'get',
    params
  })
}

export function artEditService (data) {
  return request({
    url: '/my/article/edit',
    method: 'put',
    data
  })
}
