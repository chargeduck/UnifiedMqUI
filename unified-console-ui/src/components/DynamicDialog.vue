<script setup>
import { defineModel } from 'vue'

const visible = defineModel('visible')
const title = defineModel('title')
const showFooterBtn = defineModel('showFooterBtn')
const cancelDialog = () => {
  visible.value = false
}
</script>
<template>
  <el-dialog
    v-model="visible"
    :title="title"
    :before-close="cancelDialog"
    width="70%"
    class="dynamic-modal"
  >
    <!-- 自定义头部插槽 -->
    <template #title>
      <slot name="header">
        <span>{{ title }}</span>
      </slot>
    </template>
    <!-- 动态内容区域 -->
    <slot name="component" />
    <!-- 自定义底部插槽 -->
    <template #footer>
      <slot name="footer" v-if="showFooterBtn">
        <el-button @click="visible = false">取消</el-button>
        <el-button type="primary" @click="visible = false">确认</el-button>
      </slot>
    </template>
  </el-dialog>
</template>

<style scoped>
.dynamic-modal {
  padding: 20px;
}
</style>
