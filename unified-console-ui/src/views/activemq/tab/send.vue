<script setup>
import { defineOptions, ref } from 'vue'

defineOptions({
  name: 'ActiveMqSend'
})
const msg = ref('ActiveMqSend')
</script>

<template>
  {{ msg }}
</template>
<style></style>
