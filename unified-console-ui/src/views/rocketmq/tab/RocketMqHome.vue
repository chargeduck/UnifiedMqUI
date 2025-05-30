<script setup>
import { defineOptions, inject, ref } from 'vue'
import { getConnectById } from '@/api/mqConnect.js'
import { useActiveMqStore } from '@/stores/activemq.js'

const activeMqStore = useActiveMqStore()
defineOptions({
  name: 'RocketMqHome'
})
const msg = ref('RocketMqHome')
const detailId = inject('id')
const mqDetail = ref({})
const mqExtra = ref({
  value: {}
})
const connectDetail = (id) => {
  getConnectById(id).then(resp => {
    mqDetail.value = resp.data
    mqExtra.value = JSON.parse(mqDetail.value.extra)
    activeMqStore.changeActiveConfig(resp.data)
  }).finally(() => {
    console.log(activeMqStore.configInfo, 'configInfo')
  })
}
connectDetail(detailId)
</script>
<template>
  {{ msg }}
</template>
<style></style>
