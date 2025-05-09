<script setup>
import { defineOptions, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { isBlank } from '@/utils/format.js'
import { getQueueList } from '@/api/activemq.js'
import { useActiveMqStore } from '@/stores/activemq.js'
import { storeToRefs } from 'pinia'
const activeMqStore = useActiveMqStore()

defineOptions({
  name: 'ActiveMqQueues'
})
const searchForm = ref({
  queueName: ''
})
const queues = ref([
  {
    name: 'queue1',
    pendingMsgNum: 10,
    consumersNum: 5,
    messagesEnqueued: 100,
    messagesDequeued: 50,
    views: 'view1'
  }
])

const createQueues = async () => {
  if (isBlank(searchForm.value.queueName)) {
    ElMessage.error('Queue name must be inputted')
    return
  }
  ElMessage.success(`Create queues [${ searchForm.value.queueName }] successfully`)
}
const delQueueBtnFn = () => {
  ElMessage.success('Delete queue successfully')
}
const fetchQueues = () => {
  const data = {
    config: storeToRefs(activeMqStore.activeConfig),
    queueName: searchForm.value.queueName
  }
  getQueueList(data).then(resp => {
    console.log(resp)
  })
}
fetchQueues()
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
    <el-table :data="queues" border stripe>
      <el-table-column type="index" label="Index" width="100" />
      <el-table-column prop="name" label="name" show-overflow-tooltip />
      <el-table-column prop="pendingMsgNum" label="Pending Message Number" />
      <el-table-column prop="consumersNum" label="Consumers Number" />
      <el-table-column prop="messagesEnqueued" label="Messages Enqueued" />
      <el-table-column prop="messagesDequeued" label="Messages Dequeued" />
      <el-table-column prop="views" label="Views" >
        <template #default="scope">
          <el-row :gutter="10">
            <el-col :span="12">
              <el-button type="text" size="small">Active Consumers</el-button>
            </el-col>
            <el-col :span="8">
              <el-button type="text" size="small">Browse</el-button>
            </el-col>
            <el-col :span="12">
              <el-button type="text" size="small">Active Producers</el-button>
            </el-col>
          </el-row>
        </template>
      </el-table-column>
      <el-table-column label="Operations">
        <template #default="scope">
          <el-row :gutter="10">
            <el-col :span="6">
              <el-button type="text" size="small">Send to</el-button>
            </el-col>
            <el-col :span="6">
              <el-button type="text" size="small">Purge</el-button>
            </el-col>
            <el-col :span="6">
              <el-button link type="danger"  size="small" @click="delQueueBtnFn">Delete</el-button>
            </el-col>
            <el-col :span="6">
              <el-button type="text" size="small">Pause</el-button>
            </el-col>
          </el-row>
        </template>
      </el-table-column>
    </el-table>
  </el-form>
</template>
<style></style>
