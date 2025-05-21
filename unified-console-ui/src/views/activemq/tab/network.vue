<script setup>
import { defineOptions, ref } from 'vue'

defineOptions({
  name: 'ActiveMqNetwork'
})
const tableData = ref([])
</script>

<template>
  <el-divider> Network Bridge</el-divider>
  <el-table :data="tableData" border stripe>
    <el-table-column type="index" />
    <el-table-column prop="remoteBroker" label="Remote Broker" />
    <el-table-column prop="remoteAddress" label="Remote Address" />
    <el-table-column prop="remoteBroker" label="Remote Broker" />
    <el-table-column prop="remoteBroker" label="Remote Broker" />
    <el-table-column prop="remoteBroker" label="Remote Broker" />
  </el-table>
</template>
<style></style>
