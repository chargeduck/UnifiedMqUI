import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useActiveMqStore = defineStore('activeMq', () => {
  const activeConfig = ref({})
  const changeActiveConfig = (config) => {
    activeConfig.value = config
  }
  return {
    activeConfig,
    changeActiveConfig
  }
})
