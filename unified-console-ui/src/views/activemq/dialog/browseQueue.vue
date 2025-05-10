<script setup>
import { defineOptions, ref, defineProps } from 'vue'
defineOptions({
  name: 'BrowseQueue'
})
const props = defineProps({
  data: {
    type: Object,
    default: () => {}
  }
})
const tableData = ref([])
const deleteMessage = (row) => {
  console.log(row)
  console.log(props.data)
}
</script>
<template>
  <el-table :data="tableData" border stripe>
    <el-table-column prop="messageId" label="Message ID" width="300" />
    <el-table-column prop="correlationId" label="Correlation ID"/>
    <el-table-column prop="persistence" label="Persistence"/>
    <el-table-column prop="priority" label="Priority"/>
    <el-table-column prop="replyTo" label="Reply To"/>
    <el-table-column prop="timestamp" label="Timestamp"/>
    <el-table-column prop="type" label="Type"/>
    <el-table-column label="Operations">
      <template #default="scope">
        <el-button type="primary" size="small" @click="deleteMessage(scope.row)">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>
<style></style>
