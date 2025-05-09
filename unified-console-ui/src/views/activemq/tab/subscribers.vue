<script setup>
import { defineOptions, ref } from 'vue'

defineOptions({
  name: 'ActiveMqSubscribers'
})
const msg = ref('ActiveMqSubscribers')
</script>

<template>
  {{ msg }}
</template>
<style></style>
