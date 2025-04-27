import axios from 'axios'
import { useUserInfoStore } from '@/stores/userInfo.js'
import { ElMessage } from 'element-plus'
import router from '@/router/index.js'

const baseURL = 'http://big-event-vue-api-t.itheima.net'

const request = axios.create({
  baseURL,
  timeout: 20000
})

request.interceptors.request.use(
  (config) => {
    const userInfoStore = useUserInfoStore()
    const token = userInfoStore.token
    if (token) {
      config.headers.Authorization = token
    }
    return config
  },
  (err) => {
    return Promise.reject(err)
  })

request.interceptors.response.use(
  (response) => {
    const code = response.data.code
    if (code === 0) {
      return response.data
    } else {
      ElMessage.error(response.data.msg || 'Server Error')
      return Promise.reject(response.data)
    }
  },
  (err) => {
    if (err.response?.status === 401) {
      ElMessage.error('登录过期，请重新登录')
      router.push('/login')
    }
    ElMessage.error(err || 'Server Error')
    return Promise.reject(err)
  })
export default request
export { baseURL }
