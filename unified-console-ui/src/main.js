import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { createI18n } from 'vue-i18n'
import persist from 'pinia-plugin-persistedstate'

import App from './App.vue'
import router from './router'
import '@/assets/styles/main.scss'
import 'element-plus/dist/index.css'
// 引入暗黑模式样式
import 'element-plus/theme-chalk/dark/css-vars.css'


createApp(App)
  .use(createPinia().use(persist))
  .use(router)
  .use(createI18n({

  }))
  .mount('#app')
