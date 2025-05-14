<script setup>
import { defineOptions, ref, computed, defineModel, inject } from 'vue'
import { useActiveMqStore } from '@/stores/activemq.js'
import { sendToQueue } from '@/api/activemq/queue.js'
import { ElMessage } from 'element-plus'

const activeMqStore = useActiveMqStore()
defineOptions({
  name: 'ActiveMqSendTo'
})
const props = defineProps({
  data: {
    type: Object,
    default: () => ({
      name: '',
      queueOrTopic: 'Queue',
      headerCounter: 'JMSXMessageCounter'
    })
  }
})
const visible = defineModel('visible')
const fetchQueues = inject('fetchQueues')
const defaultForm = ref({
  JMSDestination: props.data.name || '',
  JMSDestinationType: props.data.queueOrTopic || 'Topic',
  JMSCorrelationID: '',
  JMSPersistent: false,
  JMSReplyTo: '',
  JMSPriority: '',
  JMSType: '',
  JMSTimeToLive: '',
  JMSXGroupID: '',
  JMSXGroupSeq: '',
  AMQ_SCHEDULED_DELAY: 0,
  AMQ_SCHEDULED_PERIOD: 0,
  AMQ_SCHEDULED_REPEAT: 1,
  AMQ_SCHEDULED_CRON: '',
  JMSMessageCount: 1,
  JMSMessageCountHeader: 'JMSXMessageCounter',
  JMSXMessageCounter: 0,
  JMSText: ''
})
const form = ref({ ...defaultForm.value })
const gutter = ref(20)
const reset = () => {
  form.value = { ...defaultForm.value }
}
const sendTo = () => {
  const data = {
    config: activeMqStore.configInfo,
    params: {
      properties: formUrlKvStr.value,
      name: props.data.name
    }
  }
  sendToQueue(data).then(resp => {
    form.value = { ...defaultForm.value }
    ElMessage.success(`Send to queue result: ${ resp.data }`)
  }).finally(() => {
    visible.value = false
    fetchQueues()
  })
}
const formUrlKvStr = computed(() => {
  return Object.entries(form.value)
    .map(([key, value]) => `${ key }=${ value }`)
    .join(',')
})
</script>
<template>
  <el-form :model="form" label-width="150">
    <el-row :gutter="gutter">
      <el-col :span="12">
        <el-form-item label="Destination">
          <el-input v-model="form.JMSDestination" placeholder="Destination" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="Queue or Topic">
          <el-radio-group v-model="form.JMSDestinationType">
            <el-radio label="Queue">Queue</el-radio>
            <el-radio label="Topic">Topic</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="gutter">
      <el-col :span="12">
        <el-form-item label="Correlation ID">
          <el-input v-model="form.JMSCorrelationID" placeholder="Correlation ID" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="Persistent Delivery">
          <el-switch v-model="form.JMSPersistent" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="gutter">
      <el-col :span="12">
        <el-form-item label="Reply To">
          <el-input v-model="form.JMSReplyTo" placeholder="Reply To" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="Priority">
          <el-input-number :min="0" :max="9" v-model="form.JMSPriority" placeholder="Priority" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="gutter">
      <el-col :span="12">
        <el-form-item label="Type">
          <el-input v-model="form.JMSType" placeholder="Type" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="Time To Live">
          <el-input-number v-model="form.JMSTimeToLive" placeholder="Time To Live" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="gutter">
      <el-col :span="12">
        <el-form-item label="Message Group">
          <el-input v-model="form.JMSXGroupID" placeholder="Message Group" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="Group Seq">
          <el-input v-model="form.JMSXGroupSeq" placeholder="Message Group Sequence Number" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="gutter">
      <el-col :span="12">
        <el-form-item label="Delay">
          <el-input v-model="form.AMQ_SCHEDULED_DELAY" placeholder="Delay" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="Scheduling Number">
          <el-input v-model="form.AMQ_SCHEDULED_PERIOD" placeholder="Scheduling Number" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="gutter">
      <el-col :span="12">
        <el-form-item label="Repeats Number">
          <el-input-number :min="0" v-model="form.AMQ_SCHEDULED_REPEAT" placeholder="Repeats Number"
                           class="full-width" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="Cron String">
          <el-input v-model="form.AMQ_SCHEDULED_CRON" placeholder="Cron String" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="gutter">
      <el-col :span="12">
        <el-form-item label="Messages To Send">
          <el-input-number v-model="form.JMSMessageCount" placeholder="Messages To Send" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="Header Counter">
          <el-input v-model="form.JMSMessageCountHeader" placeholder="Header Counter" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="gutter">
      <el-col :span="24">
        <el-form-item label="Message Body">
          <el-input
            type="textarea"
            v-model="form.body"
            :autosize="{ minRows: 3, maxRows: 5 }"
            placeholder="Enter some text here for the message body..."
          />
        </el-form-item>
      </el-col>
    </el-row>
    <el-form-item>
      <el-button type="primary" @click="sendTo">Send</el-button>
      <el-button type="primary" @click="reset">重置</el-button>
    </el-form-item>
  </el-form>
</template>
<style></style>
