import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { createI18n } from 'vue-i18n'
import persist from 'pinia-plugin-persistedstate'
import VMdPreview from '@kangc/v-md-editor/lib/preview'
import createLineNumbertPlugin from '@kangc/v-md-editor/lib/plugins/line-number/index'
import '@kangc/v-md-editor/lib/style/preview.css'
import '@kangc/v-md-editor/lib/theme/style/github.css'

import githubTheme from '@kangc/v-md-editor/lib/theme/github.js'
import hljs from 'highlight.js'

VMdPreview.use(githubTheme, {
  Hljs: hljs
}).use(createLineNumbertPlugin())

import App from './App.vue'
import router from './router'
import '@/assets/styles/main.scss'
import 'element-plus/dist/index.css'
// 引入暗黑模式样式
import 'element-plus/theme-chalk/dark/css-vars.css'

createApp(App)
  .use(createPinia().use(persist))
  .use(router)
  .use(VMdPreview)
  .use(createI18n({}))
  .mount('#app')
