<script setup>
import { defineOptions, ref, defineProps } from 'vue'
import { useActiveMqStore } from '@/stores/activemq.js'
import { getQueueMessageList } from '@/api/activemq/queue.js'
import MessageSearch from '@/components/messageSearch.vue'

defineOptions({
  name: 'BrowseQueue'
})
const activeMqStore = useActiveMqStore()
const props = defineProps({
  data: {
    type: Object,
    default: () => {
    }
  }
})
const searchForm = ref({
  text: '',
  startTime: '',
  endTime: ''
})
const fetchMessageList = () => {
  const data = {
    config: activeMqStore.configInfo,
    params: props.data.name
  }
  console.log(searchForm.value)
  getQueueMessageList(data).then(resp => {
    tableData.value = resp.data
  })
}
fetchMessageList()
const tableData = ref([])
const deleteMessage = (row) => {
  console.log(row)
  console.log(props.data)
}

const page = ref({
  current: 1,
  size: 5,
  total: 0
})
</script>
<template>
  <message-search
    v-model:search-form="searchForm"
    v-model:do-search="fetchMessageList"
    v-model:page="page"
  />
  <el-table :data="tableData" border stripe>
    <el-table-column type="expand">
      <template #default="scope">
        <el-descriptions :column="4">
          <el-descriptions-item label="CorrelationID">
            {{ scope.row.jmscorrelationID }}
          </el-descriptions-item>
          <el-descriptions-item label="Group Id">
            {{ scope.row.jmsxgroupID }}
          </el-descriptions-item>
          <el-descriptions-item label="Group Seq">
            {{ scope.row.jmsxgroupSeq }}
          </el-descriptions-item>
          <el-descriptions-item label="Delivery Mode">
            {{ scope.row.jmsDeliveryMode }}
          </el-descriptions-item>
          <el-descriptions-item label="Deliveryd">
            {{ scope.row.delivered }}
          </el-descriptions-item>
          <el-descriptions-item label="Expiration">
            {{ scope.row.jmsexpiration }}
          </el-descriptions-item>
          <el-descriptions-item label="Destination">
            {{ scope.row.jmsdestination }}
          </el-descriptions-item>
          <el-descriptions-item v-model="scope.row.jmsxuserID" label="User Id">
            {{ scope.row.jmsxuserID }}
          </el-descriptions-item>
        </el-descriptions>
      </template>
    </el-table-column>
    <el-table-column prop="jmsmessageID" label="Message ID" width="300" />
    <el-table-column prop="text" label="Message" show-overflow-tooltip/>
    <el-table-column prop="jmspriority" label="Priority" />
    <el-table-column prop="jmsreplyTo" label="Reply To" />
    <el-table-column prop="jmstimestamp" label="Timestamp" show-overflow-tooltip/>
    <el-table-column prop="jmstype" label="Type" />
    <el-table-column label="Operations">
      <template #default="scope">
        <el-button type="primary" size="small" @click="deleteMessage(scope.row)">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
    v-model:current-page="page.current"
    :page-size="page.size"
    layout="total, prev, pager, next, jumper"
    :total="page.total"
  />
</template>
<style></style>
