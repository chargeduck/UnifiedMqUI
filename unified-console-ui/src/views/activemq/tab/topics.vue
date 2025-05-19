<script setup>
import { defineOptions, ref, provide } from 'vue'
import { addTopic, getTopicList, removeTopic } from '@/api/activemq/broker.js'
import { gbFilter, isBlank } from '@/utils/format.js'
import DynamicDialog from '@/components/DynamicDialog.vue'
import SendTo from '@/components/activemq/sendTo.vue'
import ActiveProducers from '@/views/activemq/dialog/topic/activeProducers.vue'
import ActiveSubscribers from '@/views/activemq/dialog/topic/activeSubscribers.vue'
import { ElMessage } from 'element-plus'
import { commonQuery } from '@/utils/commonQuery.js'

defineOptions({
  name: 'ActiveMqTopics'
})
const searchForm = ref({
  name: ''
})
const page = ref({
  current: 1,
  size: 5,
  total: 0
})
const dynamicDialogProps = ref({
  title: '',
  component: null,
  visible: false,
  data: null,
  showFooterBtn: false
})

const tableData = ref([])
const fetchTopics = () => {
  const data = commonQuery(searchForm.value.name, page.value)

  getTopicList(data).then(resp => {
    tableData.value = resp.data.records
    page.value = {
      current: resp.data.current,
      size: resp.data.size,
      total: resp.data.total
    }
  })
}
const reset = () => {
  searchForm.value = {
    name: ''
  }
  page.value = {
    current: 1,
    size: 5,
    total: 0
  }
  fetchTopics()
}

const handleCurrentChange = (val) => {
  page.value.current = val
  fetchTopics()
}
const handleSizeChange = (val) => {
  page.value.size = val
  fetchTopics()
}
const doSendToTopic = (data) => {
  data.JMSDestinationType = 'Topic'
  dynamicDialogProps.value = {
    title: `Send To Topic ${ data.name }`,
    component: SendTo,
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
const activeSubscribers = (data) => {
  dynamicDialogProps.value = {
    title: `Active Subscribers for ${ data.name }`,
    component: ActiveSubscribers,
    visible: true,
    data,
    showFooterBtn: false
  }
}
const doDelete = (row) => {
  const data = commonQuery(row.name, null)
  removeTopic(data).then(resp => {
    ElMessage.success(`Delete result: ${ resp.msg }`)
  }).finally(() => {
    fetchTopics()
  })
}
const createTopic = () => {
  if (isBlank(searchForm.value.name)) {
    ElMessage.error('Queue name must be inputted')
    return
  }
  const data = commonQuery(searchForm.value.name, null)
  addTopic(data).then(resp => {
    ElMessage.success(`Create queue successfully ${ resp.msg }`)
  }).finally(() => {
    searchForm.value.name = ''
    fetchTopics()
  })
}
fetchTopics()
provide('fetchFn', fetchTopics)
</script>

<template>
  <el-form :model="searchForm" :inline="true">
    <el-form-item label="Topic Name">
      <el-input
        v-model="searchForm.name"
        placeholder="Topic Name"
        @change="fetchTopics"
        @blur="fetchTopics"
        clearable />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="fetchTopics">Search</el-button>
      <el-button type="primary" @click="reset">Reset</el-button>
      <el-button type="primary" @click="createTopic">Create</el-button>
    </el-form-item>
  </el-form>
  <el-table :data="tableData" border stripe>
    <el-table-column type="expand">
      <template #default="scope">
        <el-descriptions :column="4">
          <el-descriptions-item label="storeMessageSize">
            {{ scope.row.storeMessageSize }}
          </el-descriptions-item>
          <el-descriptions-item label="averageEnqueueTime">
            {{ scope.row.averageEnqueueTime }}
          </el-descriptions-item>
          <el-descriptions-item label="tempUsagePercentUsage">
            {{ scope.row.tempUsagePercentUsage }}
          </el-descriptions-item>
          <el-descriptions-item label="maxProducersToAudit">
            {{ scope.row.maxProducersToAudit }}
          </el-descriptions-item>
          <el-descriptions-item label="memoryPercentUsage">
            {{ scope.row.memoryPercentUsage }}
          </el-descriptions-item>
          <el-descriptions-item label="memoryUsagePortion">
            {{ scope.row.memoryUsagePortion }}
          </el-descriptions-item>
          <el-descriptions-item label="alwaysRetroactive">
            {{ scope.row.alwaysRetroactive }}
          </el-descriptions-item>
          <el-descriptions-item label="blockedProducerWarningInterval">
            {{ scope.row.blockedProducerWarningInterval }}
          </el-descriptions-item>
          <el-descriptions-item label="sendDuplicateFromStoreToDLQ">
            {{ scope.row.sendDuplicateFromStoreToDLQ }}
          </el-descriptions-item>
          <el-descriptions-item label="averageMessageSize">
            {{ scope.row.averageMessageSize }}
          </el-descriptions-item>
          <el-descriptions-item label="slowConsumerStrategy">
            {{ scope.row.slowConsumerStrategy }}
          </el-descriptions-item>
          <el-descriptions-item label="producerFlowControl">
            {{ scope.row.producerFlowControl }}
          </el-descriptions-item>
          <el-descriptions-item label="memoryUsageByteCount">
            {{ scope.row.memoryUsageByteCount }}
          </el-descriptions-item>
          <el-descriptions-item label="prioritizedMessages">
            {{ scope.row.prioritizedMessages }}
          </el-descriptions-item>
          <el-descriptions-item label="totalBlockedTime">
            {{ scope.row.totalBlockedTime }}
          </el-descriptions-item>
          <el-descriptions-item label="duplicateFromStoreCount">
            {{ scope.row.duplicateFromStoreCount }}
          </el-descriptions-item>
          <el-descriptions-item label="dlq">
            {{ scope.row.dlq }}
          </el-descriptions-item>
          <el-descriptions-item label="blockedSends">
            {{ scope.row.blockedSends }}
          </el-descriptions-item>
          <el-descriptions-item label="maxAuditDepth">
            {{ scope.row.maxAuditDepth }}
          </el-descriptions-item>
          <el-descriptions-item label="inFlightCount">
            {{ scope.row.inFlightCount }}
          </el-descriptions-item>
          <el-descriptions-item label="maxPageSize">
            {{ scope.row.maxPageSize }}
          </el-descriptions-item>
          <el-descriptions-item label="memoryLimit">
            {{ gbFilter(scope.row.memoryLimit) }}
          </el-descriptions-item>
          <el-descriptions-item label="tempUsageLimit">
            {{ gbFilter(scope.row.tempUsageLimit) }}
          </el-descriptions-item>
          <el-descriptions-item label="forwardCount">
            {{ scope.row.forwardCount }}
          </el-descriptions-item>
          <el-descriptions-item label="queueSize">
            {{ scope.row.queueSize }}
          </el-descriptions-item>
          <el-descriptions-item label="expiredCount">
            {{ scope.row.expiredCount }}
          </el-descriptions-item>
          <el-descriptions-item label="subscriptions">
            {{ scope.row.subscriptions }}
          </el-descriptions-item>
          <el-descriptions-item label="minMessageSize">
            {{ scope.row.minMessageSize }}
          </el-descriptions-item>
          <el-descriptions-item label="minEnqueueTime">
            {{ scope.row.minEnqueueTime }}
          </el-descriptions-item>
          <el-descriptions-item label="dispatchCount">
            {{ scope.row.dispatchCount }}
          </el-descriptions-item>
          <el-descriptions-item label="maxEnqueueTime">
            {{ scope.row.maxEnqueueTime }}
          </el-descriptions-item>
          <el-descriptions-item label="options">
            {{ scope.row.options }}
          </el-descriptions-item>
          <el-descriptions-item label="maxMessageSize">
            {{ scope.row.maxMessageSize }}
          </el-descriptions-item>
          <el-descriptions-item label="useCache">
            {{ scope.row.useCache }}
          </el-descriptions-item>
        </el-descriptions>
      </template>
    </el-table-column>
    <el-table-column type="index" label="Index" width="80" />
    <el-table-column prop="name" label="Name" show-overflow-tooltip>
      <template #default="scope">
        <el-link type="primary" :underline="false" @click="doSendToTopic(scope.row)">
          {{ scope.row.name }}
        </el-link>
      </template>
    </el-table-column>
    <el-table-column prop="consumerCount" label="ConsumerCount" />
    <el-table-column prop="producerCount" label="ProducerCount" />
    <el-table-column prop="enqueueCount" label="Messages Enqueued " />
    <el-table-column prop="dequeueCount" label="Messages Dequeued " />
    <el-table-column label="Operations">
      <template #default="scope">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-button link type="primary" size="small" @click="doSendToTopic(scope.row)">Send To</el-button>
          </el-col>
          <el-col :span="12">
            <el-button type="text" size="small" @click="activeSubscribers(scope.row)">Active Subscribers</el-button>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-button link type="danger" size="small" @click="doDelete(scope.row)">Delete</el-button>
          </el-col>
          <el-col :span="12">
            <el-button type="text" size="small" @click="activeProducer(scope.row)">Active Producers</el-button>
          </el-col>
        </el-row>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
    class="pagination-margin"
    v-model:current-page="page.current"
    v-model:page-size="page.size"
    :page-sizes="[5, 8, 10]"
    layout="total, sizes, prev, pager, next, jumper"
    :total="page.total"
    @current-change="handleCurrentChange"
    @size-change="handleSizeChange"
  />
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
