<script setup>
import { defineOptions, ref, defineProps } from 'vue'

defineOptions({
  name: 'ActiveProducers'
})
const props = defineProps({
  data: {
    type: Object,
    default: () => {
    }
  }
})
console.log(props.data)

const tableData = ref([])

</script>
<template>
  <el-table :data="tableData" border stripe>
    <el-table-column prop="clientId" label="Client ID" show-overflow-tooltip />
    <el-table-column prop="connectionId" label="Connection Id" show-overflow-tooltip />
    <el-table-column prop="sessionId" label="SessionId" />
    <el-table-column prop="producerId" label="ProducerId" />
    <el-table-column prop="producerWindowSize" label="ProducerWindowSize" show-overflow-tooltip />
    <el-table-column prop="dispatchAsync" label="DispatchAsync" />
    <el-table-column prop="blocked" label="Blocked" />
    <el-table-column prop="blockedTime" label="BlockedTime" />
    <el-table-column prop="sentCount" label="SentCount" />
  </el-table>
</template>
<style></style>
