import { defineStore } from 'pinia'
import { reactive, computed } from 'vue'
export const useUserInfoStore = defineStore('userInfo', () => {
  const userInfo = reactive({
    id: '',
    nickname: '',
    token: '',
    avatar: ''
  })
  const setUserInfo = (info) => {
    userInfo.id = info.id
    userInfo.nickname = info.nickname
    userInfo.token = info.token
    userInfo.avatar = info.avatar
  }
  const getUserInfo = () => {
    return userInfo
  }
  const removeToken = () => {
    userInfo.token = ''
  }
  const token = computed(() => {
    return userInfo.token
  })
  return {
    userInfo,
    getUserInfo,
    setUserInfo,
    removeToken,
    token
  }
}, {
  persist: true
})
