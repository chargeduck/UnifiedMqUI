<script setup>
import { defineOptions, ref, defineProps } from 'vue'

defineOptions({
  name: 'ActiveConsumers'
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
    <el-table-column prop="sessionId" label="Session Id" show-overflow-tooltip />
    <el-table-column prop="selector" label="Selector" />
    <el-table-column prop="enqueues" label="Enqueues" />
    <el-table-column prop="dequeues" label="Dequeues" show-overflow-tooltip />
    <el-table-column prop="dispatched" label="Dispatched" />
    <el-table-column prop="dispatchedQueue" label="Dispatched Queue" />
    <el-table-column prop="prefetchMaxPending" label="PrefetchMaxPending" />
    <el-table-column prop="exclusiveRetroactive" label="exclusiveRetroactive" />
  </el-table>
</template>
<style></style>
