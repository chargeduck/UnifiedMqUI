<script setup>
import { defineOptions, inject } from 'vue'

defineOptions({
  name: 'ActiveSubscribersTable'
})
const fetchFn = inject('fetchFn')
const page = defineModel('page')
const tableData = defineModel('tableData')
const searchForm = defineModel('searchForm')
const handleCurrentChange = (val) => {
  page.value.current = val
  fetchFn()
}
const handleSizeChange = (val) => {
  page.value.size = val
  fetchFn()
}
const resetForm = () => {
  searchForm.value.subscriptionName = ''
  fetchFn()
}
fetchFn()
</script>
<template>
  <el-form :model="searchForm" :inline="true" style="margin-bottom: 20px">
    <el-form-item label="Subscription Name">
      <el-input
        v-model="searchForm.subscriptionName"
        placeholder="Enter Subscription Name"
        clearable
        @clear="fetchFn"
        @blur="fetchFn"
      />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="fetchFn">Search</el-button>
      <el-button @click="resetForm">Reset</el-button>
    </el-form-item>
  </el-form>
  <el-table :data="tableData" border stripe>
    <el-table-column type="expand">
      <template #default="scope">
        <el-descriptions :column="3">
          <el-descriptions-item label="priority">
            {{ scope.row.priority }}
          </el-descriptions-item>
          <el-descriptions-item label="connection">
            {{ scope.row.connection }}
          </el-descriptions-item>
          <el-descriptions-item label="userName">
            {{ scope.row.userName }}
          </el-descriptions-item>
          <el-descriptions-item label="destinationName">
            {{ scope.row.destinationName }}
          </el-descriptions-item>
          <el-descriptions-item label="cursorFull">
            {{ scope.row.cursorFull }}
          </el-descriptions-item>
          <el-descriptions-item label="dequeueCounter">
            {{ scope.row.dequeueCounter }}
          </el-descriptions-item>
          <el-descriptions-item label="durable">
            {{ scope.row.durable }}
          </el-descriptions-item>
          <el-descriptions-item label="subscriptionId">
            {{ scope.row.subscriptionId }}
          </el-descriptions-item>
          <el-descriptions-item label="noLocal">
            {{ scope.row.noLocal }}
          </el-descriptions-item>
          <el-descriptions-item label="network">
            {{ scope.row.network }}
          </el-descriptions-item>
          <el-descriptions-item label="exclusive">
            {{ scope.row.exclusive }}
          </el-descriptions-item>
          <el-descriptions-item label="slowConsumer">
            {{ scope.row.slowConsumer }}
          </el-descriptions-item>
          <el-descriptions-item label="retroactive">
            {{ scope.row.retroactive }}
          </el-descriptions-item>
          <el-descriptions-item label="consumedCount">
            {{ scope.row.consumedCount }}
          </el-descriptions-item>
          <el-descriptions-item label="enqueueCounter">
            {{ scope.row.enqueueCounter }}
          </el-descriptions-item>
          <el-descriptions-item label="prefetchSize">
            {{ scope.row.prefetchSize }}
          </el-descriptions-item>
          <el-descriptions-item label="destinationQueue">
            {{ scope.row.destinationQueue }}
          </el-descriptions-item>
          <el-descriptions-item label="dispatchedCounter">
            {{ scope.row.dispatchedCounter }}
          </el-descriptions-item>
          <el-descriptions-item label="cursorMemoryUsage">
            {{ scope.row.cursorMemoryUsage }}
          </el-descriptions-item>
          <el-descriptions-item label="destinationTemporary">
            {{ scope.row.destinationTemporary }}
          </el-descriptions-item>
          <el-descriptions-item label="cursorPercentUsage">
            {{ scope.row.cursorPercentUsage }}
          </el-descriptions-item>
          <el-descriptions-item label="messageCountAwaitingAcknowledge">
            {{ scope.row.messageCountAwaitingAcknowledge }}
          </el-descriptions-item>
          <el-descriptions-item label="maximumPendingMessageLimit">
            {{ scope.row.maximumPendingMessageLimit }}
          </el-descriptions-item>
          <el-descriptions-item label="destinationTopic">
            {{ scope.row.destinationTopic }}
          </el-descriptions-item>
          <el-descriptions-item label="dispatchedQueueSize">
            {{ scope.row.dispatchedQueueSize }}
          </el-descriptions-item>
        </el-descriptions>
      </template>
    </el-table-column>
    <el-table-column prop="clientId" label="Client ID" show-overflow-tooltip />
    <el-table-column prop="connectionId" label="Connection Id" show-overflow-tooltip />
    <el-table-column prop="sessionId" label="SessionId" />
    <el-table-column prop="active" label="active" />
    <el-table-column prop="network" label="Network" />
    <el-table-column prop="pendingQueueSize" label="Pending Queue Size" />
    <el-table-column prop="subscriptionName" label="Subscription Name" />
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
</template>
<style></style>
