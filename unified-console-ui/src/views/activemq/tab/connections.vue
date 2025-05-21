<script setup>
import { defineOptions, ref } from 'vue'
import { commonQuery } from '@/utils/commonQuery.js'
import { fetchConnectList, fetchNetworkConnectorList } from '@/api/activemq/connection.js'
import DynamicDialog from '@/components/DynamicDialog.vue'
import ConnectionDetail from '@/views/activemq/dialog/connectionDetail.vue'

defineOptions({
  name: 'ActiveMqConnections'
})
const tableData = ref([])
const networkTableData = ref([])
const searchForm = ref({
  type: ''
})
const page = ref({
  current: 1,
  size: 10,
  total: 0
})
const typeOptions = [
  'ws',
  'openwire',
  'stomp',
  'amqp',
  'mqtt'
]
const doSearch = () => {
  const data = commonQuery(searchForm.value.type, page.value)
  fetchConnectList(data).then(resp => {
    tableData.value = resp.data.records
    page.value = {
      current: resp.data.current,
      size: resp.data.size,
      total: resp.data.total
    }
  })
}
const doNetworkSearch = () => {
  const data = commonQuery('', page.value)
  fetchNetworkConnectorList(data).then(resp => {
    networkTableData.value = resp.data
  })
}
doNetworkSearch()
const handleCurrentChange = (val) => {
  page.value.current = val
  doSearch()
}
const handleSizeChange = (val) => {
  page.value.size = val
  doSearch()
}
const showDetail = (row) => {
  dynamicDialogProps.value = {
    visible: true,
    title: `Connection ${ row.clientId }`,
    showFooterBtn: false,
    data: row,
    component: ConnectionDetail
  }
}
const dynamicDialogProps = ref({
  visible: false,
  title: '',
  showFooterBtn: false,
  component: '',
  data: {}
})
doSearch()
</script>

<template>
  <el-form :inline="true" :model="searchForm">
    <el-form-item label="Type">
      <el-select
        clearable
        filterable
        @blur="doSearch"
        v-model="searchForm.type"
        placeholder="Please select">
        <el-option
          v-for="item in typeOptions"
          :key="item"
          :label="item"
          :value="item" />
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="doSearch">Search</el-button>
    </el-form-item>

  </el-form>
  <el-table :data="tableData" border stripr>
    <el-table-column type="index" label="index" width="100" />
    <el-table-column prop="clientId" label="name">
      <template #default="scope">
        <el-link type="primary" @click="showDetail(scope.row)">{{ scope.row.clientId }}</el-link>
      </template>
    </el-table-column>
    <el-table-column prop="remoteAddress" label="RemoteAddress" />
    <el-table-column prop="active" label="Active" />
    <el-table-column prop="slow" label="slow" />
    <el-table-column prop="connectorType" label="Type" />
  </el-table>
  <el-pagination
    v-model:current-page="page.current"
    :page-size="page.size"
    layout="total, prev, pager, next, sizes"
    :total="page.total"
    @current-change="handleCurrentChange"
    @size-change="handleSizeChange"
  />

  <dynamic-dialog
    v-model:visible="dynamicDialogProps.visible"
    :title="dynamicDialogProps.title"
    :showFooterBtn="dynamicDialogProps.showFooterBtn"
    :destroy-on-close="true"
    :width="80"
  >
    <template #component>
      <component
        :is="dynamicDialogProps.component"
        :data="dynamicDialogProps.data"
        v-model:visible="dynamicDialogProps.visible"
      />
    </template>
  </dynamic-dialog>
  <el-divider content-position="left" style="margin-top: 50px">Network Connectors</el-divider>
  <el-table :data="networkTableData" border stripr>
    <el-table-column type="expand">
      <template #default="scope">
        <el-descriptions :column="4">
          <el-descriptions-item label="userName">{{ scope.row.userName }}</el-descriptions-item>
          <el-descriptions-item label="password">{{ scope.row.password }}</el-descriptions-item>
          <el-descriptions-item label="duplex">{{ scope.row.duplex }}</el-descriptions-item>
          <el-descriptions-item label="remoteUserName">{{ scope.row.remoteUserName }}</el-descriptions-item>
          <el-descriptions-item label="remotePassword">{{ scope.row.remotePassword }}</el-descriptions-item>
          <el-descriptions-item label="advisoryPrefetchSize">{{ scope.row.advisoryPrefetchSize }}</el-descriptions-item>
          <el-descriptions-item label="suppressDuplicateTopicSubscriptions">
            {{ scope.row.suppressDuplicateTopicSubscriptions }}
          </el-descriptions-item>
          <el-descriptions-item label="suppressDuplicateQueueSubscriptions">
            {{ scope.row.suppressDuplicateQueueSubscriptions }}
          </el-descriptions-item>
          <el-descriptions-item label="prefetchSize">{{ scope.row.prefetchSize }}</el-descriptions-item>
        </el-descriptions>

      </template>
    </el-table-column>
    <el-table-column type="index" label="index" width="100" />
    <el-table-column prop="name" label="name" />
    <el-table-column prop="messageTTL" label="messageTTL" />
    <el-table-column prop="consumerTTL" label="consumerTTL" />
    <el-table-column prop="dynamicOnly" label="dynamicOnly" />
    <el-table-column prop="conduitSubscriptions" label="conduitSubscription" />
    <el-table-column prop="bridgeTempDestinations" label="bridgeTemp" />
    <el-table-column prop="decreaseNetworkConsumerPriority" label="decreasePriority" />
    <el-table-column prop="dispatchAsync" label="dispatchAsync" />
  </el-table>
</template>
<style></style>
