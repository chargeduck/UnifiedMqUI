<script setup>
import { defineOptions, ref, provide, watch } from 'vue'
import { useRouter } from 'vue-router'
import { navMenuList } from '@/utils/const.js'
import { Moon, Sunny } from '@element-plus/icons-vue'
import { useDark, usePreferredDark } from '@vueuse/core'

const router = useRouter()
defineOptions({
  name: 'LayoutIndex'
})
const headerTitle = ref('Unified MQ Console')
provide('headerTitle', headerTitle)
const handleSelect = (key) => {
  router.push(key)
}
const isSystemDark = usePreferredDark()
const theme = ref(isSystemDark.value)
console.log(isSystemDark.value, 'systemDark')
// 使用 useDark 检测系统偏好主题
const isDark = useDark({
  selector: 'html', // 应用到 body 元素
  attribute: 'class', // 使用 class 来切换主题
  valueDark: 'dark', // 暗色主题的 class
  valueLight: '' // 浅色主题的 class
})
watch(isSystemDark, (newValue) => {
  isDark.value = newValue
})
watch(theme, (newValue) => {
  isDark.value = newValue
})
isDark.value = isSystemDark.value
</script>
<template>
  <el-container class="layout-container">

    <el-container>
      <el-header>
        <div>
          <span style="font-weight: bolder; margin-right: 10px">{{ headerTitle }}</span>
          <el-switch
            v-model="theme"
            inline-prompt
            style="float: right"
            :active-icon="Moon"
            :inactive-icon="Sunny"
          />
        </div>

        <el-menu
          :default-active="$route.path"
          class="el-menu-demo"
          mode="horizontal"
          :ellipsis="false"
          @select="handleSelect"
        >
          <el-menu-item
            v-for="(item, index) in navMenuList"
            :key="index"
            :index="item.index"
          >
            <span v-if="item.textMenu">{{ item.tooltip }}</span>
            <el-tooltip effect="dark" content="Mqtt" placement="top" v-else>
              <el-image
                :style="{width: item.width, height: item.height}"
                :src="item.logo"
                :alt="item.tooltip"
              />
            </el-tooltip>
          </el-menu-item>
        </el-menu>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
      <el-footer>Unified Mq Console ©2025 Created by Lesscoding</el-footer>
    </el-container>
  </el-container>
</template>

<style lang="scss" scoped>
.layout-container {
  height: 100vh;

  .el-header {
    display: flex;
    align-items: center;
    justify-content: space-between;

    .el-dropdown__box {
      display: flex;
      align-items: center;

      .el-icon {
        margin-left: 10px;
      }

      &:active,
      &:focus {
        outline: none;
      }
    }
  }

  .el-footer {
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 14px;
  }
}
</style>
