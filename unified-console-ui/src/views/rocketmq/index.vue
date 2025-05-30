<script setup>
import { defineOptions, ref, provide } from 'vue'
import { useRoute } from 'vue-router'
import RocketMqHome from '@/views/rocketmq/tab/RocketMqHome.vue'

const route = useRoute()
defineOptions({
  name: 'RocketMqIndex'
})
const activeName = ref('home')
const id = route.query.id
const lazy = ref(true)
provide('id', id)
</script>
<template>
  <el-tabs v-model="activeName" type="border-card">
    <el-tab-pane label="Home" name="home">
      <rocket-mq-home />
    </el-tab-pane>
  </el-tabs>
</template>
<style></style>
