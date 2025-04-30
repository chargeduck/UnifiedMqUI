<script setup>
import { defineOptions, ref, provide } from 'vue'
import { useRouter } from 'vue-router'
import { navMenuList } from '@/utils/const.js'

const router = useRouter()
defineOptions({
  name: 'LayoutIndex'
})
const headerTitle = ref('Unified MQ Console')
provide('headerTitle', headerTitle)
const handleSelect = (key, keyPath) => {
  console.log(key, keyPath)
  router.push(key)
}
</script>
<template>
  <el-container class="layout-container">

    <el-container>
      <el-header>
        <div>
          <strong>{{ headerTitle }}</strong>
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

  .el-aside {
    background-color: #232323;

    &__logo {
      height: 120px;
      background: url('@/assets/logo.png') no-repeat center / 120px auto;
    }

    .el-menu {
      border-right: none;
    }
  }

  .el-header {
    background-color: #fff;
    display: flex;
    align-items: center;
    justify-content: space-between;

    .el-dropdown__box {
      display: flex;
      align-items: center;

      .el-icon {
        color: #999;
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
    color: #666;
  }
}
</style>
