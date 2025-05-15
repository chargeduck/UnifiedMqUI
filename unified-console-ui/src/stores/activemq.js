import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useActiveMqStore = defineStore('activeMq', () => {
  const activeConfig = ref({})
  const changeActiveConfig = (config) => {
    activeConfig.value = config
    activeConfig.value.extra = ''
  }
  const configInfo = computed(() => {
    return activeConfig.value
  })
  return {
    activeConfig,
    changeActiveConfig,
    configInfo
  }
}, {
  persist: true
})
