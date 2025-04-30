<script setup>
import { defineOptions, ref } from 'vue'
import ActiveMqHome from '@/views/activemq/tab/home.vue'
defineOptions({
  name: 'ActiveMqIndex'
})
const activeName = ref('home')
</script>
<template>
<el-tabs v-model="activeName" type="border-card">
  <el-tab-pane label="Home" name="home">
    <ActiveMqHome />
  </el-tab-pane>
  <el-tab-pane label="Queues" name="queues" />
  <el-tab-pane label="Topics" name="topics" />
  <el-tab-pane label="Subscribers" name="subscribers" />
  <el-tab-pane label="Connections" name="connections" />
  <el-tab-pane label="Network" name="network" />
  <el-tab-pane label="Scheduled" name="scheduled" />
  <el-tab-pane label="Send" name="send" />
</el-tabs>
</template>
<style></style>
