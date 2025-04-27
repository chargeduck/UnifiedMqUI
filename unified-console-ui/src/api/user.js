import request from '@/utils/request'

export function userUpdatePasswordService (data) {
  return request({
    url: '/my/updatepwd',
    method: 'patch',
    data
  })
}


export function userRegisterService (data) {
  return request({
    url: '/api/reg',
    method: 'post',
    data
  })
}

export function userLoginService (data) {
  return request({
    url: '/api/login',
    method: 'post',
    data
  })
}

export function userUpdateAvatarService (data) {
  return request({
    url: '/my/update/avatar',
    method: 'patch',
    data
  })
}

export function userUpdateInfoService () {
}
