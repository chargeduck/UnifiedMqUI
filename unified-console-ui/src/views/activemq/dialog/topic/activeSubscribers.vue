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
    <el-table-column prop="subscriptionId " label="SubscriptionId" />
    <el-table-column prop="selector" label="Selector" show-overflow-tooltip />
    <el-table-column prop="active" label="active" />
    <el-table-column prop="network" label="Network" />
    <el-table-column prop="pendingQueueSize" label="Pending Queue Size" />
    <el-table-column prop="inflight" label="Inflight" />
    <el-table-column prop="enqueued" label="Enqueued" />
    <el-table-column prop="dequeued" label="Dequeued" />
    <el-table-column prop="prefetch" label="Prefetch" />
    <el-table-column prop="subscriptionName" label="Subscription Name" />
  </el-table>
</template>
<style></style>
