import { createApp } from 'vue'
import { createPinia } from 'pinia'
import persist from 'pinia-plugin-persistedstate'

import App from './App.vue'
import router from './router'
import '@/assets/styles/main.scss'
import 'element-plus/dist/index.css'
// 引入暗黑模式样式
import 'element-plus/theme-chalk/dark/css-vars.css'


const app = createApp(App)

app.use(createPinia().use(persist))
app.use(router)

app.mount('#app')
