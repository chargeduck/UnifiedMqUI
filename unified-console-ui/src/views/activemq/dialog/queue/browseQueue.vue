<script setup>
import { defineOptions, ref, defineProps, onMounted } from 'vue'
import { getQueueMessageList, removeMessage } from '@/api/activemq/queue.js'
import MessageSearch from '@/components/messageSearch.vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { commonQuery } from '@/utils/commonQuery.js'

defineOptions({
  name: 'BrowseQueue'
})
const props = defineProps({
  data: {
    type: Object,
    default: () => {
    }
  }
})
const page = ref({
  current: 1,
  size: 5,
  total: 0
})
const searchForm = ref({
  text: '',
  startTime: '',
  endTime: ''
})
const fetchMessageList = () => {
  const data = commonQuery(
    {
      name: props.data.name,
      ...searchForm.value
    },
    page.value
  )
  getQueueMessageList(data).then(resp => {
    tableData.value = resp.data.records
    page.value = {
      current: resp.data.current,
      size: resp.data.size,
      total: resp.data.total
    }
  })
}
onMounted(() => {
  fetchMessageList()
})
const tableData = ref([])
const doDeleteMessage = (row) => {
  ElMessageBox.confirm('Are you sure you want to delete this message?', 'Warning', {
    confirmButtonText: 'OK',
    cancelButtonText: 'Cancel',
    type: 'warning'
  }).then(() => {
    const data = commonQuery({
      name: props.data.name,
      messageId: row.jmsmessageID
    }, null)
    removeMessage(data).then(resp => {
      ElMessage.success(`Delete message successfully ${ resp.data }`)
    }).finally(() => {
      fetchMessageList()
    })
  })
}
const handleCurrentChange = (val) => {
  page.value.current = val
  fetchMessageList()
}
const handleSizeChange = (val) => {
  page.value.size = val
  fetchMessageList()
}

</script>
<template>
  <message-search
    class="search-form-margin"
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
          <el-descriptions-item label="User Id">
            {{ scope.row.jmsxuserID }}
          </el-descriptions-item>
          <el-descriptions-item label="Message">
            {{ scope.row.text }}
          </el-descriptions-item>
        </el-descriptions>
      </template>
    </el-table-column>
    <el-table-column prop="jmsmessageID" label="Message ID" width="310" show-overflow-tooltip />
    <el-table-column prop="text" label="Message" show-overflow-tooltip />
    <el-table-column prop="jmspriority" label="Priority" />
    <el-table-column prop="jmsreplyTo" label="Reply To" />
    <el-table-column prop="jmstimestamp" label="Timestamp" show-overflow-tooltip />
    <el-table-column prop="jmstype" label="Type" />
    <el-table-column label="Operations">
      <template #default="scope">
        <el-button type="danger" size="small" @click="doDeleteMessage(scope.row)">Delete</el-button>
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

</template>
<style></style>
