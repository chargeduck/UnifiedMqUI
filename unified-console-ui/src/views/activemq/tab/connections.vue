<script setup>
import { defineOptions, ref } from 'vue'

defineOptions({
  name: 'ActiveMqConnections'
})
const msg = ref('ActiveMqConnections')
</script>

<template>
  {{ msg }}
</template>
<style></style>
