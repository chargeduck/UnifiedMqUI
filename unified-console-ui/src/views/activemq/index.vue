<script setup>
import { defineOptions, ref, provide } from 'vue'
import { useRoute } from 'vue-router'
import { ElMessage } from 'element-plus'
import ActiveMqHome from '@/views/activemq/tab/home.vue'
import ActiveMqQueues from '@/views/activemq/tab/queues.vue'
import ActiveMqTopics from '@/views/activemq/tab/topics.vue'
import ActiveMqNetwork from '@/views/activemq/tab/network.vue'
import ActiveMqSend from '@/views/activemq/tab/send.vue'
import ActiveMqScheduled from '@/views/activemq/tab/scheduled.vue'
import ActiveMqSubscribers from '@/views/activemq/tab/subscribers.vue'
import ActiveMqConnections from '@/views/activemq/tab/connections.vue'

const route = useRoute()
defineOptions({
  name: 'ActiveMqIndex'
})
const activeName = ref('home')
const id = route.query.id
ElMessage.success(`当前查询的连接ID为：${ id }`)
provide('id', id)
</script>
<template>
  <el-tabs v-model="activeName" type="border-card">
    <el-tab-pane label="Home" name="home">
      <active-mq-home />
    </el-tab-pane>
    <el-tab-pane label="Queues" name="queues">
      <active-mq-queues />
    </el-tab-pane>
    <el-tab-pane label="Topics" name="topics">
      <active-mq-topics />
    </el-tab-pane>
    <el-tab-pane label="Subscribers" name="subscribers">
      <active-mq-subscribers />
    </el-tab-pane>
    <el-tab-pane label="Connections" name="connections">
      <active-mq-connections />
    </el-tab-pane>
    <el-tab-pane label="Network" name="network">
      <active-mq-network />
    </el-tab-pane>
    <el-tab-pane label="Scheduled" name="scheduled">
      <active-mq-scheduled />
    </el-tab-pane>
    <el-tab-pane label="Send" name="send">
      <active-mq-send />
    </el-tab-pane>
  </el-tabs>
</template>
<style></style>
