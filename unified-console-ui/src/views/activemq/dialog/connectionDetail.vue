<script setup>
import { defineOptions, defineProps, ref } from 'vue'
import { commonQuery } from '@/utils/commonQuery.js'
import { connectionConsumers } from '@/api/activemq/connection.js'

defineOptions({
  name: 'ConnectionDetail'
})
const props = defineProps({
  data: {
    type: Object,
    default: () => {
    }
  }
})
const tableData = ref([])
const page = ref({
  current: 1,
  size: 5,
  total: 0
})
const doSearch = () => {
  const data = commonQuery(props.data.consumers, page.value)
  connectionConsumers(data).then(resp => {
    tableData.value = resp.data.records
    page.value = {
      current: resp.data.current,
      size: resp.data.size,
      total: resp.data.total
    }
  })
}
doSearch()
const handleCurrentChange = (val) => {
  page.value.current = val
  doSearch()
}
const handleSizeChange = (val) => {
  page.value.size = val
  doSearch()
}
</script>
<template>
  <el-divider content-position="left">Connection Detail</el-divider>
  <el-descriptions :column="3">
    <el-descriptions-item label="connectorType">{{ props.data.connectorType }}</el-descriptions-item>
    <el-descriptions-item label="userName">{{ props.data.userName }}</el-descriptions-item>
    <el-descriptions-item label="connected">{{ props.data.connected }}</el-descriptions-item>
    <el-descriptions-item label="active">{{ props.data.active }}</el-descriptions-item>
    <el-descriptions-item label="clientId">{{ props.data.clientId }}</el-descriptions-item>
    <el-descriptions-item label="remoteAddress">{{ props.data.remoteAddress }}</el-descriptions-item>
    <el-descriptions-item label="slow">{{ props.data.slow }}</el-descriptions-item>
    <el-descriptions-item label="blocked">{{ props.data.blocked }}</el-descriptions-item>
    <el-descriptions-item label="dispatchQueueSize">{{ props.data.dispatchQueueSize }}</el-descriptions-item>
    <el-descriptions-item label="activeTransactionCount">{{ props.data.activeTransactionCount }}</el-descriptions-item>
    <el-descriptions-item label="oldestActiveTransactionDuration">{{ props.data.oldestActiveTransactionDuration }}
    </el-descriptions-item>
  </el-descriptions>
  <el-divider content-position="left">Consumers</el-divider>
  <el-table :data="tableData" border stripe>
    <el-table-column type="expand">
      <template #default="scope">
        <el-descriptions :column="4">
          <el-descriptions-item label="priority">{{ scope.row.priority }}</el-descriptions-item>
          <el-descriptions-item label="active">{{ scope.row.active }}</el-descriptions-item>
          <el-descriptions-item label="subscriptionName">{{ scope.row.subscriptionName }}</el-descriptions-item>
          <el-descriptions-item label="dispatchAsync">{{ scope.row.dispatchAsync }}</el-descriptions-item>
          <el-descriptions-item label="connectionId">{{ scope.row.connectionId }}</el-descriptions-item>
          <el-descriptions-item label="durable">{{ scope.row.durable }}</el-descriptions-item>
          <el-descriptions-item label="Acknowledge">{{ scope.row.messageCountAwaitingAcknowledge }}</el-descriptions-item>
          <el-descriptions-item label="maximumPendingQueueSize">{{ scope.row.maximumPendingQueueSize }}</el-descriptions-item>
          <el-descriptions-item label="destinationTemporary">{{ scope.row.destinationTemporary }}</el-descriptions-item>
          <el-descriptions-item label="destinationTopic">{{ scope.row.destinationTopic }}</el-descriptions-item>
          <el-descriptions-item label="destinationQueue">{{ scope.row.destinationQueue }}</el-descriptions-item>
          <el-descriptions-item label="pendingQueueSize">{{ scope.row.pendingQueueSize }}</el-descriptions-item>
          <el-descriptions-item label="discardedCount">{{ scope.row.discardedCount }}</el-descriptions-item>
          <el-descriptions-item label="consumedCount">{{ scope.row.consumedCount }}</el-descriptions-item>
          <el-descriptions-item label="network">{{ scope.row.network }}</el-descriptions-item>
          <el-descriptions-item label="slowConsumer">{{ scope.row.slowConsumer }}</el-descriptions-item>
          <el-descriptions-item label="subscriptionId">{{ scope.row.subscriptionId }}</el-descriptions-item>
          <el-descriptions-item label="subscriptionId">{{ scope.row.subscriptionId }}</el-descriptions-item>
          <el-descriptions-item label="noLocal">{{ scope.row.noLocal }}</el-descriptions-item>
        </el-descriptions>
      </template>
    </el-table-column>
    <el-table-column prop="destinationName" label="Destination" />
    <el-table-column prop="sessionId" label="SessionId" />
    <el-table-column prop="selector" label="Selector" />
    <el-table-column prop="enqueueCounter" label="Enqueues" />
    <el-table-column prop="dequeueCounter" label="Dequeues" />
    <el-table-column prop="dispatchedCounter" label="Dispatched" />
    <el-table-column prop="dispatchedQueueSize" label="Dispatched Queue" />
    <el-table-column prop="prefetchSize" label="PrefetchSize " />
    <el-table-column prop="maximumPendingMessageLimit" label="Max pending " />
    <el-table-column prop="exclusive" label="Exclusive" />
    <el-table-column prop="retroactive" label="Retroactive" />
  </el-table>
  <el-pagination
    v-model:current-page="page.current"
    :page-size="page.size"
    layout="total, prev, pager, next"
    :total="page.total"
    @current-change="handleCurrentChange"
    @size-change="handleSizeChange"
  />
</template>
<style></style>
