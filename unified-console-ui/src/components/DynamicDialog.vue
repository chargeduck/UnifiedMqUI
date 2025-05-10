<script setup>
import { ref, defineModel, watch } from 'vue'

const visible = defineModel('visible')
const title = defineModel('title')
const data = defineModel('data')
const showFooterBtn = defineModel('showFooterBtn')

// 传递给组件的数据
const modalData = ref(data)

watch(() => data, (newData) => {
  modalData.value = newData
})
</script>
<template>
  <el-dialog
    v-model="visible"
    :title="title"
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
