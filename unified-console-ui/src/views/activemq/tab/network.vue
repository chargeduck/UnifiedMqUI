<script setup>
import { defineOptions, ref } from 'vue'
import { fetchNetworkBridgeList } from '@/api/activemq/connection.js'
import { commonQuery } from '@/utils/commonQuery.js'

defineOptions({
  name: 'ActiveMqNetwork'
})
const searchForm = ref({
  host: ''
})
const tableData = ref([])
const page = ref({
  current: 1,
  size: 10,
  total: 0
})
const doSearch = () => {
  const data = commonQuery(searchForm.value.host, page.value)
  fetchNetworkBridgeList(data).then(resp => {
    tableData.value = resp.data.records
    page.value = {
      current: resp.data.current,
      size: resp.data.size,
      total: resp.data.total
    }
  })
}
const handleCurrentChange = (val) => {
  page.value.current = val
  doSearch()
}
const handleSizeChange = (val) => {
  page.value.size = val
  doSearch()
}
doSearch()
</script>

<template>
  <el-divider> Network Bridge</el-divider>
  <el-form :model="searchForm" label-width="100px" :inline="true">
    <el-form-item label="Host">
      <el-input v-model="searchForm.host" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" clearable @blur="doSearch" @change="doSearch" @click="doSearch">Search</el-button>
    </el-form-item>
  </el-form>
  <el-table :data="tableData" border stripe>

    <el-table-column type="expand" >
      <template #default="scope">
        <el-descriptions :column="3">
          <el-descriptions-item label="localBrokerName">{{ scope.row.localBrokerName }}</el-descriptions-item>
          <el-descriptions-item label="localAddress">{{ scope.row.localAddress }}</el-descriptions-item>
          <el-descriptions-item label="remoteBrokerId">{{ scope.row.remoteBrokerId }}</el-descriptions-item>
        </el-descriptions>
      </template>
    </el-table-column>
    <el-table-column type="index" />
    <el-table-column prop="remoteAddress" label="remoteAddress" />
    <el-table-column prop="remoteBrokerName" label="remoteBrokerName" />
    <el-table-column prop="receivedCounter" label="receivedCounter" />
    <el-table-column prop="enqueueCounter" label="enqueueCounter" />
    <el-table-column prop="dequeueCounter" label="dequeueCounter" />
    <el-table-column prop="createdByDuplex" label="createdByDuplex" />
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
