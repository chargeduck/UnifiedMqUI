<script setup>
import {defineOptions, inject, ref} from 'vue'
import {getConnectById} from '@/api/mqConnect.js'
import {gbFilter} from '@/utils/format.js'

defineOptions({
  name: 'ActiveMqHome'
})
const detailId = inject('id')
const mqDetail = ref({})
const mqExtra = ref({
  value: {}
})
const connectDetail = (id) => {
  getConnectById(id).then(resp => {
    mqDetail.value = resp.data
    mqExtra.value = JSON.parse(mqDetail.value.extra)
  })
}
connectDetail(detailId)
</script>
<template>
  <el-descriptions :column="4" border>

    <el-descriptions-item label="连接名称"> {{ mqDetail.title }}</el-descriptions-item>
    <el-descriptions-item label="主机"> {{ mqDetail.host }}</el-descriptions-item>
    <el-descriptions-item label="端口"> {{ mqDetail.port }}</el-descriptions-item>
    <el-descriptions-item label="版本"> {{ mqDetail.version }}</el-descriptions-item>

    <el-descriptions-item label="用户名"> {{ mqDetail.username }}</el-descriptions-item>
    <el-descriptions-item label="密码"> {{ mqDetail.password }}</el-descriptions-item>
    <el-descriptions-item label="存活时间"> {{ mqDetail.upTime }}</el-descriptions-item>
    <el-descriptions-item label="数据目录">
      <el-text size="small">{{ mqExtra.value.DataDirectory }}</el-text>
    </el-descriptions-item>

    <el-descriptions-item label="连接状态">
      <el-switch
        v-model="mqDetail.activeFlag"
        disabled
        style="--el-switch-on-color: #13ce66;--el-switch-off-color: #ff4949" />
    </el-descriptions-item>
    <el-descriptions-item label="启用统计">
      <el-switch
        v-model="mqExtra.value.StatisticsEnabled"
        disabled
        style="--el-switch-on-color: #13ce66;--el-switch-off-color: #ff4949" />
    </el-descriptions-item>
    <el-descriptions-item label="Persistent">
      <el-switch
        v-model="mqExtra.value.Persistent"
        disabled
        style="--el-switch-on-color: #13ce66;--el-switch-off-color: #ff4949" />
    </el-descriptions-item>
    <el-descriptions-item label="Slave">
      <el-switch
        v-model="mqExtra.value.Slave"
        disabled
        style="--el-switch-on-color: #13ce66;--el-switch-off-color: #ff4949" />
    </el-descriptions-item>

    <el-descriptions-item label="连接总数">{{ mqExtra.value.TotalConnectionsCount }}</el-descriptions-item>
    <el-descriptions-item label="当前连接数">{{ mqExtra.value.CurrentConnectionsCount }}
    </el-descriptions-item>
    <el-descriptions-item label="生产者总数">{{ mqExtra.value.TotalProducerCount }}</el-descriptions-item>
    <el-descriptions-item label="消费者总数">{{ mqExtra.value.TotalConsumerCount }}</el-descriptions-item>

    <el-descriptions-item label="最大存储">{{ gbFilter(mqExtra.value.StoreLimit) }}</el-descriptions-item>
    <el-descriptions-item label="内存限制">{{ gbFilter(mqExtra.value.MemoryLimit) }}</el-descriptions-item>
    <el-descriptions-item label="缓存限制">{{ gbFilter(mqExtra.value.TempLimit) }}</el-descriptions-item>
    <el-descriptions-item label="任务调度限制">{{ mqExtra.value.JobSchedulerStoreLimit }}</el-descriptions-item>

    <el-descriptions-item label="消息总数">{{ mqExtra.value.TotalMessageCount }}</el-descriptions-item>
    <el-descriptions-item label="入队消息总数">{{ mqExtra.value.TotalEnqueueCount }}</el-descriptions-item>
    <el-descriptions-item label="出队消息总数">{{ mqExtra.value.TotalDequeueCount }}</el-descriptions-item>
    <el-descriptions-item label="JMS作业调度">{{ mqExtra.value.JMSJobScheduler }}</el-descriptions-item>

    <el-descriptions-item label="消息平均大小">{{ mqExtra.value.AverageMessageSize }}</el-descriptions-item>
    <el-descriptions-item label="最小消息大小">{{ mqExtra.value.MinMessageSize }}</el-descriptions-item>
    <el-descriptions-item label="最大消息大小">{{ mqExtra.value.MaxMessageSize }}</el-descriptions-item>
    <el-descriptions-item label="VmUrl">{{ mqExtra.value.VMURL }}</el-descriptions-item>

    <el-descriptions-item label="临时存储使用率">{{ mqExtra.value.TempPercentUsage }}</el-descriptions-item>
    <el-descriptions-item label="任务调度器存储使用率">{{ mqExtra.value.JobSchedulerStorePercentUsage }}
      <el-descriptions-item label="存储使用率">{{ mqExtra.value.StorePercentUsage }}</el-descriptions-item>
      <el-descriptions-item label="存活时长">{{ mqExtra.value.UptimeMillis }} ms</el-descriptions-item>
    </el-descriptions-item>


    <el-descriptions-item label="连接ID"> {{ mqDetail.brokerId }}</el-descriptions-item>
  </el-descriptions>
  <el-divider />
  <el-descriptions :column="1" border style="margin-top: 20px">
    <el-descriptions-item label="连接信息">
      <el-row
        v-for="(item, index) in mqExtra.value.TransportConnectors"
        :key="index">
        {{ item }}
      </el-row>
    </el-descriptions-item>
    <el-descriptions-item label="主题">
      <el-row
        v-for="(item, index) in mqExtra.value.Topics"
        :key="index">
        {{ item }}
      </el-row>
    </el-descriptions-item>
    <el-descriptions-item label="主题生产者">{{ mqExtra.value.TopicProducers }}</el-descriptions-item>
    <el-descriptions-item label="主题订阅者">
      <el-row
        v-for="(item, index) in mqExtra.value.TopicSubscribers"
        :key="index">
        <el-text size="small"> {{ item }}</el-text>
      </el-row>
    </el-descriptions-item>
    <el-descriptions-item label="持久化主题订阅者">{{ mqExtra.value.DurableTopicSubscribers }}
    </el-descriptions-item>
    <el-descriptions-item label="非活跃主题订阅者">
      {{ mqExtra.value.InactiveDurableTopicSubscribers }}
    </el-descriptions-item>

    <el-descriptions-item label="临时主题">{{ mqExtra.value.TemporaryTopics }}</el-descriptions-item>
    <el-descriptions-item label="临时主题生产者">
      <el-row
        v-for="(item, index) in mqExtra.value.TemporaryTopicProducers"
        :key="index">
        {{ item }}
      </el-row>
    </el-descriptions-item>
    <el-descriptions-item label="临时主题订阅者">{{ mqExtra.value.TemporaryTopicSubscribers }}
    </el-descriptions-item>

    <el-descriptions-item label="队列">
      <el-row
        v-for="(item, index) in mqExtra.value.Topics"
        :key="index">
        {{ item }}
      </el-row>
    </el-descriptions-item>
    <el-descriptions-item label="队列生产者">{{ mqExtra.value.QueueProducers }}</el-descriptions-item>
    <el-descriptions-item label="队列订阅者">{{ mqExtra.value.QueueSubscribers }}</el-descriptions-item>

    <el-descriptions-item label="临时队列">{{ mqExtra.value.TemporaryQueues }}</el-descriptions-item>
    <el-descriptions-item label="临时队列生产者">{{ mqExtra.value.TemporaryQueueProducers }}
    </el-descriptions-item>
    <el-descriptions-item label="临时队列订阅者">
      <el-row
        v-for="(item, index) in mqExtra.value.TemporaryQueueSubscribers"
        :key="index">
        {{ item }}
      </el-row>
    </el-descriptions-item>
    <el-descriptions-item label="动态目的生产者">{{ mqExtra.value.DynamicDestinationProducers }}
    </el-descriptions-item>
  </el-descriptions>
</template>
<style></style>
