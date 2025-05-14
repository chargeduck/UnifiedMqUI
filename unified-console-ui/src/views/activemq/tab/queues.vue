<script setup>
import { defineOptions, ref, onBeforeMount, provide } from 'vue'
import { isBlank, gbFilter } from '@/utils/format.js'
import { pauseQueue, purgeQueue, resumeQueue } from '@/api/activemq/queue.js'
import { addQueue, getQueueList, removeQueue } from '@/api/activemq/broker.js'
import { ElMessage } from 'element-plus'
import { useActiveMqStore } from '@/stores/activemq.js'
import DynamicDialog from '@/components/DynamicDialog.vue'
import SendTo from '@/views/activemq/dialog/queue/sendTo.vue'
import BrowseQueue from '@/views/activemq/dialog/queue/browseQueue.vue'
import ActiveConsumers from '@/views/activemq/dialog/queue/activeConsumers.vue'
import ActiveProducers from '@/views/activemq/dialog/queue/activeProducers.vue'

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
  const data = {
    config: activeMqStore.configInfo,
    params: searchForm.value.queueName
  }
  addQueue(data).then(resp => {
    ElMessage.success(`Create queue successfully ${ resp.msg }`)
  }).finally(() => {
    searchForm.value.queueName = ''
    fetchQueues()
  })
}
const doRemoveQueue = (row) => {
  const data = {
    config: activeMqStore.configInfo,
    params: row.name
  }
  removeQueue(data).then(resp => {
    ElMessage.success(`Pause queue successfully ${ resp.msg }`)
  }).finally(() => {
    fetchQueues()
  })
}
const fetchQueues = () => {
  const data = {
    config: activeMqStore.configInfo,
    params: searchForm.value.queueName
  }
  getQueueList(data).then(resp => {
    queues.value = resp.data
  })
}
const activeConsumer = (data) => {
  dynamicDialogProps.value = {
    title: `Active Consumers for ${ data.name }`,
    component: ActiveConsumers,
    visible: true,
    data,
    showFooterBtn: false
  }
}
const activeProducer = (data) => {
  dynamicDialogProps.value = {
    title: `Active Producers for ${ data.name }`,
    component: ActiveProducers,
    visible: true,
    data,
    showFooterBtn: false
  }
}
const browseQueue = (data) => {
  dynamicDialogProps.value = {
    title: `Browse Queue ${ data.name }`,
    component: BrowseQueue,
    visible: true,
    data,
    showFooterBtn: false
  }
}
const doPauseQueue = (row) => {
  const data = {
    config: activeMqStore.configInfo,
    params: row.name
  }
  pauseQueue(data).then(resp => {
    ElMessage.success(`Pause queue successfully ${ resp.msg }`)
  }).finally(() => {
    fetchQueues()
  })
}
const doResumeQueue = (row) => {
  const data = {
    config: activeMqStore.configInfo,
    params: row.name
  }
  resumeQueue(data).then(resp => {
    ElMessage.success(`Pause queue successfully ${ resp.msg }`)
  }).finally(() => {
    fetchQueues()
  })
}

const doPurgeQueue = (row) => {
  const data = {
    config: activeMqStore.configInfo,
    params: row.name
  }
  purgeQueue(data).then(resp => {
    ElMessage.success(`Pause queue successfully ${ resp.msg }`)
  }).finally(() => {
    fetchQueues()
  })
}
const doSendToQueue = (data) => {
  dynamicDialogProps.value = {
    title: `Send To Queue ${ data.name }`,
    component: SendTo,
    visible: true,
    data,
    showFooterBtn: false
  }
}
onBeforeMount(() => {
  setTimeout(() => {
    fetchQueues()
  }, 200)
})

const dynamicDialogProps = ref({
  title: '',
  component: null,
  visible: false,
  data: null,
  showFooterBtn: false
})
provide('fetchQueues', fetchQueues)
</script>

<template>
  <el-form :model="searchForm" inline>
    <el-form-item label="Queue Name" prop="queueName">
      <el-input v-model="searchForm.queueName" placeholder="请输入队列名称" @blur="fetchQueues" clearable />
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
            <el-button link type="primary" size="small" @click="doSendToQueue(scope.row)">Send to</el-button>
          </el-col>
          <el-col :span="12">
            <el-button link type="danger" size="small" @click="doPurgeQueue(scope.row)">Purge</el-button>
          </el-col>
          <el-col :span="12">
            <el-button link type="danger" size="small" @click="doRemoveQueue(scope.row)">Delete</el-button>
          </el-col>
          <el-col :span="12" v-show="!scope.row.paused">
            <el-button link type="danger" size="small" @click="doPauseQueue(scope.row)">Pause</el-button>
          </el-col>
          <el-col :span="12" v-show="scope.row.paused">
            <el-button link type="danger" size="small" @click="doResumeQueue(scope.row)">Resume</el-button>
          </el-col>
        </el-row>
      </template>
    </el-table-column>
  </el-table>
  <dynamic-dialog
    v-model:visible="dynamicDialogProps.visible"
    :title="dynamicDialogProps.title"
    :showFooterBtn="dynamicDialogProps.showFooterBtn"
    :destroy-on-close="true"
  >
    <template #component>
      <component
        :is="dynamicDialogProps.component"
        :data="dynamicDialogProps.data"
        v-model:visible="dynamicDialogProps.visible"
      />
    </template>
  </dynamic-dialog>
</template>
<style></style>
