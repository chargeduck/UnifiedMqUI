<script setup>
import { defineOptions, ref, onBeforeMount } from 'vue'
import { ElMessage } from 'element-plus'
import { isBlank, gbFilter } from '@/utils/format.js'
import { getQueueList } from '@/api/activemq.js'
import { useActiveMqStore } from '@/stores/activemq.js'
import DynamicDialog from '@/components/DynamicDialog.vue'
import SendTo from '@/views/activemq/dialog/sendTo.vue'

const activeMqStore = useActiveMqStore()

defineOptions({
  name: 'ActiveMqQueues'
})
const searchForm = ref({
  queueName: ''
})
const queues = ref([])

const createQueues = async () => {
  if (isBlank(searchForm.value.queueName)) {
    ElMessage.error('Queue name must be inputted')
    return
  }
  ElMessage.success(`Create queues [${ searchForm.value.queueName }] successfully`)
}
const deleteQueue = (row) => {
  ElMessage.success(`Delete queue successfully ${row}`)
}
const fetchQueues = () => {
  const data = {
    config: activeMqStore.configInfo,
    queueName: searchForm.value.queueName
  }
  getQueueList(data).then(resp => {
    queues.value = resp.data
  })
}
const activeConsumer = (row) => {
  console.log(row)
}
const activeProducer = (row) => {
  console.log(row)
}
const browseQueue = (row) => {
  console.log(row)
}
const pauseQueue = (row) => {
  console.log(row)
}
const purgeQueue = (row) => {
  console.log(row)
}
const sendToQueue = (row) => {
  dynamicDialogProps.value = {
    title: 'Send To Queue',
    visible: true,
    component: SendTo,
    data: row,
    showFooterBtn: false
  }
}
onBeforeMount(() => {
  setTimeout(() => {
    fetchQueues()
  }, 1500)
})

const dynamicDialogProps = ref({
  title: '',
  visible: false,
  component: null,
  row: null,
  showFooterBtn: false
})
</script>

<template>
  <el-form :model="searchForm" inline>
    <el-form-item label="Queue Name" prop="queueName">
      <el-input v-model="searchForm.queueName" placeholder="请输入队列名称" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="fetchQueues">Search</el-button>
      <el-button type="primary" @click="createQueues">Create</el-button>
    </el-form-item>
  </el-form>
  <el-table :data="queues" border stripe>
    <el-table-column type="expand">
      <template #default="scope">
        <el-descriptions :column="5">
          <el-descriptions-item label="ProducerFlowControl">
            {{ scope.row.producerFlowControl }}
          </el-descriptions-item>
          <el-descriptions-item label="Options">
            {{ scope.row.options }}
          </el-descriptions-item>
          <el-descriptions-item label="AlwaysRetroactive">
            {{ scope.row.alwaysRetroactive }}
          </el-descriptions-item>
          <el-descriptions-item label="MemoryUsageByteCount">
            {{ scope.row.memoryUsageByteCount }}
          </el-descriptions-item>
          <el-descriptions-item label="AverageBlockedTime">
            {{ scope.row.averageBlockedTime }}
          </el-descriptions-item>
          <el-descriptions-item label="MemoryPercentUsage">
            {{ scope.row.memoryPercentUsage }}
          </el-descriptions-item>
          <el-descriptions-item label="CursorMemoryUsage">
            {{ scope.row.cursorMemoryUsage }}
          </el-descriptions-item>
          <el-descriptions-item label="InFlightCount">
            {{ scope.row.inFlightCount }}
          </el-descriptions-item>
          <el-descriptions-item label="SendDuplicateFromStoreToDLQ">
            {{ scope.row.sendDuplicateFromStoreToDLQ }}
          </el-descriptions-item>
          <el-descriptions-item label="CacheEnabled">
            {{ scope.row.cacheEnabled }}
          </el-descriptions-item>
          <el-descriptions-item label="ForwardCount">
            {{ scope.row.forwardCount }}
          </el-descriptions-item>
          <el-descriptions-item label="StoreMessageSize">
            {{ scope.row.storeMessageSize }}
          </el-descriptions-item>
          <el-descriptions-item label="DLQ">
            {{ scope.row.dlq }}
          </el-descriptions-item>
          <el-descriptions-item label="AverageEnqueueTime">
            {{ scope.row.averageEnqueueTime }}
          </el-descriptions-item>
          <el-descriptions-item label="MaxAuditDepth">
            {{ scope.row.maxAuditDepth }}
          </el-descriptions-item>
          <el-descriptions-item label="TotalBlockedTime">
            {{ scope.row.totalBlockedTime }}
          </el-descriptions-item>
          <el-descriptions-item label="BlockedSends">
            {{ scope.row.blockedSends }}
          </el-descriptions-item>
          <el-descriptions-item label="MaxPageSize">
            {{ scope.row.maxPageSize }}
          </el-descriptions-item>
          <el-descriptions-item label="TempUsageLimit">
            {{ gbFilter(scope.row.tempUsageLimit) }}
          </el-descriptions-item>
          <el-descriptions-item label="PrioritizedMessages">
            {{ scope.row.prioritizedMessages }}
          </el-descriptions-item>
          <el-descriptions-item label="MemoryUsagePortion">
            {{ scope.row.memoryUsagePortion }}
          </el-descriptions-item>
          <el-descriptions-item label="Paused">
            {{ scope.row.paused }}
          </el-descriptions-item>
          <el-descriptions-item label="TempUsagePercentUsage">
            {{ scope.row.tempUsagePercentUsage }}
          </el-descriptions-item>
          <el-descriptions-item label="AverageMessageSize">
            {{ scope.row.averageMessageSize }}
          </el-descriptions-item>
          <el-descriptions-item label="CursorFull">
            {{ scope.row.cursorFull }}
          </el-descriptions-item>
          <el-descriptions-item label="MaxProducersToAudit">
            {{ scope.row.maxProducersToAudit }}
          </el-descriptions-item>
          <el-descriptions-item label="ExpiredCount">
            {{ scope.row.expiredCount }}
          </el-descriptions-item>
          <el-descriptions-item label="CursorPercentUsage">
            {{ scope.row.cursorPercentUsage }}
          </el-descriptions-item>
          <el-descriptions-item label="DuplicateFromStoreCount">
            {{ scope.row.duplicateFromStoreCount }}
          </el-descriptions-item>
          <el-descriptions-item label="MinEnqueueTime">
            {{ scope.row.minEnqueueTime }}
          </el-descriptions-item>
          <el-descriptions-item label="MemoryLimit">
            {{ gbFilter(scope.row.memoryLimit) }}
          </el-descriptions-item>
          <el-descriptions-item label="MinMessageSize">
            {{ scope.row.minMessageSize }}
          </el-descriptions-item>
          <el-descriptions-item label="DispatchCount">
            {{ scope.row.dispatchCount }}
          </el-descriptions-item>
          <el-descriptions-item label="MaxEnqueueTime">
            {{ scope.row.maxEnqueueTime }}
          </el-descriptions-item>
          <el-descriptions-item label="BlockedProducerWarningInterval">
            {{ scope.row.blockedProducerWarningInterval }}
          </el-descriptions-item>
          <el-descriptions-item label="MessageGroupType">
            {{ scope.row.messageGroupType }}
          </el-descriptions-item>
          <el-descriptions-item label="MaxMessageSize">
            {{ scope.row.maxMessageSize }}
          </el-descriptions-item>
          <el-descriptions-item label="UseCache">
            {{ scope.row.useCache }}
          </el-descriptions-item>
          <el-descriptions-item label="SlowConsumerStrategy">
            {{ scope.row.slowConsumerStrategy }}
          </el-descriptions-item>
          <el-descriptions-item label="Subscriptions">
            {{ scope.row.subscriptions }}
          </el-descriptions-item>
        </el-descriptions>
      </template>
    </el-table-column>
    <el-table-column type="index" label="Index" width="100" />
    <el-table-column prop="name" label="name" show-overflow-tooltip />
    <el-table-column prop="queueSize" label="Pending Message Number" />
    <el-table-column prop="consumerCount" label="Consumers Number" />
    <el-table-column prop="producerCount" label="Producers Number" />
    <el-table-column prop="enqueueCount" label="Messages Enqueued" />
    <el-table-column prop="dequeueCount" label="Messages Dequeued" />
    <el-table-column prop="views" label="Views" width="210">
      <template #default="scope">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-button type="primary" link size="small" @click="activeConsumer(scope.row)">Active Consumers</el-button>
          </el-col>
          <el-col :span="12">
            <el-button type="primary" link size="small" @click="browseQueue(scope.row)">Browse</el-button>
          </el-col>
          <el-col :span="12">
            <el-button type="primary" link size="small" @click="activeProducer(scope.row)">Active Producers</el-button>
          </el-col>
        </el-row>
      </template>
    </el-table-column>
    <el-table-column label="Operations">
      <template #default="scope">
        <el-row>
          <el-col :span="12">
            <el-button link type="primary" size="small" @click="sendToQueue(scope.row)">Send to</el-button>
          </el-col>
          <el-col :span="12">
            <el-button link type="primary" size="small" @click="purgeQueue(scope.row)">Purge</el-button>
          </el-col>
          <el-col :span="12">
            <el-button link type="danger" size="small" @click="deleteQueue(scope.row)">Delete</el-button>
          </el-col>
          <el-col :span="12">
            <el-button link type="danger" size="small" @click="pauseQueue(scope.row)">Pause</el-button>
          </el-col>
        </el-row>
      </template>
    </el-table-column>
  </el-table>
  <dynamic-dialog
    :visible="dynamicDialogProps.visible"
    :row="dynamicDialogProps.row"
    :title="dynamicDialogProps.title"
    :showFooterBtn="dynamicDialogProps.showFooterBtn"
  >
    <template #component>
      <component :is="dynamicDialogProps.component" />
    </template>
  </dynamic-dialog>
</template>
<style></style>
