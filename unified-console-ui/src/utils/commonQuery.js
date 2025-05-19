import { useActiveMqStore } from '@/stores/activemq.js'

const activeMqStore = useActiveMqStore()
export const commonQuery = (params, page) => {
  return {
    config: activeMqStore.configInfo,
    params,
    page
  }
}
