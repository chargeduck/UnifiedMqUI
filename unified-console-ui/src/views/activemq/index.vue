<script setup>
import { defineOptions, ref, provide } from 'vue'
import { useRoute } from 'vue-router'
import ActiveMqHome from '@/views/activemq/tab/home.vue'
import ActiveMqQueues from '@/views/activemq/tab/queues.vue'
import ActiveMqTopics from '@/views/activemq/tab/topics.vue'
import ActiveMqNetwork from '@/views/activemq/tab/network.vue'
import ActiveMqScheduled from '@/views/activemq/tab/scheduled.vue'
import ActiveMqSubscribers from '@/views/activemq/tab/subscribers.vue'
import ActiveMqConnections from '@/views/activemq/tab/connections.vue'
import SendTo from '@/components/activemq/sendTo.vue'

const route = useRoute()
defineOptions({
  name: 'ActiveMqIndex'
})
const activeName = ref('home')
const id = route.query.id
const lazy = ref(true)
provide('id', id)
</script>
<template>
  <el-tabs v-model="activeName" type="border-card">
    <el-tab-pane label="Home" name="home">
      <active-mq-home />
    </el-tab-pane>
    <el-tab-pane label="Queues" name="queues" :lazy="lazy">
      <active-mq-queues />
    </el-tab-pane>
    <el-tab-pane label="Topics" name="topics" :lazy="lazy">
      <active-mq-topics />
    </el-tab-pane>
    <el-tab-pane label="Subscribers" name="subscribers" :lazy="lazy">
      <active-mq-subscribers />
    </el-tab-pane>
    <el-tab-pane label="Connections" name="connections" :lazy="lazy">
      <active-mq-connections />
    </el-tab-pane>
    <el-tab-pane label="Network" name="network" :lazy="lazy">
      <active-mq-network />
    </el-tab-pane>
    <el-tab-pane label="Scheduled" name="scheduled" :lazy="lazy">
      <active-mq-scheduled />
    </el-tab-pane>
    <el-tab-pane label="Send" name="send" :lazy="lazy">
      <send-to />
    </el-tab-pane>
  </el-tabs>
</template>
<style></style>
