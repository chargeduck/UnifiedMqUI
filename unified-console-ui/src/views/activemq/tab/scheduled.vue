<script setup>
import { defineOptions, ref } from 'vue'
import { allJobs, jobScheduler, removeJob } from '@/api/activemq/scheduler.js'
import { commonQuery } from '@/utils/commonQuery.js'
import { isBlank } from '@/utils/format.js'
import VMdPreview from '@kangc/v-md-editor/lib/preview'
import { scheduledMd } from '@/utils/const.js'
import { ElMessage } from 'element-plus'


defineOptions({
  name: 'ActiveMqScheduled'
})
const searchResult = ref({})
const searchForm = ref({
  start: '',
  end: ''
})
const page = ref({
  pageNum: 1,
  pageSize: 10,
  total: 0
})
const tableData = ref([])
const showScheduledTable = ref(false)
const checkScheduled = () => {
  jobScheduler(commonQuery(null, null)).then(resp => {
    searchResult.value = resp.data
  }).finally(() => {
    if (!isBlank(searchResult.value)) {
      fetchScheduledList()
      showScheduledTable.value = true
    } else {
      showScheduledTable.value = false
    }
  })

}
const fetchScheduledList = () => {
  const data = commonQuery({
    objectName: searchResult.value.objectName,
    start: searchForm.value.start,
    end: searchForm.value.end
  }, page.value)
  allJobs(data).then(resp => {
    tableData.value = resp.data.records
    page.value = {
      current: resp.data.current,
      size: resp.data.size,
      total: resp.data.total
    }
  })
}
const doDelete = (row) => {
  const data = commonQuery({
    objectName: searchResult.value.objectName,
    jobId: row.jobId
  }, null)
  removeJob(data).then(resp => {
    ElMessage.success(`Delete result: ${ resp.msg }`)
  }).finally(() => {
    fetchScheduledList()
  })
}
checkScheduled()
</script>

<template>
  <div v-if="showScheduledTable">
    <el-table :data="tableData" border stripe>
      <el-table-column type="index" label="Index" width="100" />
      <el-table-column prop="jobId" label="jobId" width="300" />
      <el-table-column prop="cronEntry" label="cronEntry" />
      <el-table-column prop="next" label="next" />
      <el-table-column prop="start" label="start" />
      <el-table-column prop="delay" label="delay" />
      <el-table-column prop="period" label="period" />
      <el-table-column prop="repeat" label="repeat" />
      <el-table-column label="Operation">
        <template #default="scope">
          <el-button type="danger" size="small" @click="doDelete(scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
  <div v-else>
    <el-divider content-position="left">Scheduled not Started!</el-divider>
    <VMdPreview :text="scheduledMd" />
  </div>
</template>
<style></style>
