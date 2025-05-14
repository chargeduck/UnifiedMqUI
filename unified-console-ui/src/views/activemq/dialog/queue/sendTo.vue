<script setup>
import { defineOptions, ref } from 'vue'

defineOptions({
  name: 'ActiveMqSendTo'
})
const props = defineProps({
  data: {
    type: Object,
    default: () => ({
      destination: '',
      queueOrTopic: 'Topic',
      headerCounter: 'JMSXMessageCounter'
    })
  }
})
const defaultForm = ref({
  destination: props.destination || '',
  queueOrTopic: props.queueOrTopic || 'Topic',
  correlationId: '',
  persistentDelivery: false,
  replyTo: '',
  priority: '',
  type: '',
  timeToLive: '',
  messageGroup: '',
  messageGroupSequenceNumber: '',
  delay: 0,
  schedulingNumber: 0,
  repeatsNumber: 1,
  cronString: '',
  messagesToSend: 1,
  headerCounter: 'JMSXMessageCounter'
})
const form = ref({ ...defaultForm.value })
const gutter = ref(20)
const reset = () => {
  form.value = { ...defaultForm.value }
}
const sendTo = () => {
  console.log(form.value)
}
</script>
<template>
  <el-form :model="form" label-width="150">
    <el-row :gutter="gutter">
      <el-col :span="12">
        <el-form-item label="Destination">
          <el-input v-model="form.destination" placeholder="Destination" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="Queue or Topic">
          <el-radio-group v-model="form.queueOrTopic">
            <el-radio label="Queue">Queue</el-radio>
            <el-radio label="Topic">Topic</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="gutter">
      <el-col :span="12">
        <el-form-item label="Correlation ID">
          <el-input v-model="form.correlationId" placeholder="Correlation ID" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="Persistent Delivery">
          <el-switch v-model="form.persistentDelivery" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="gutter">
      <el-col :span="12">
        <el-form-item label="Reply To">
          <el-input v-model="form.replyTo" placeholder="Reply To" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="Priority">
          <el-input v-model="form.priority" placeholder="Priority" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="gutter">
      <el-col :span="12">
        <el-form-item label="Type">
          <el-input v-model="form.type" placeholder="Type" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="Time To Live">
          <el-input v-model="form.timeToLive" placeholder="Time To Live" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="gutter">
      <el-col :span="12">
        <el-form-item label="Message Group">
          <el-input v-model="form.messageGroup" placeholder="Message Group" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="Group Seq">
          <el-input v-model="form.messageGroupSequenceNumber" placeholder="Message Group Sequence Number" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="gutter">
      <el-col :span="12">
        <el-form-item label="Delay">
          <el-input v-model="form.delay" placeholder="Delay" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="Scheduling Number">
          <el-input v-model="form.schedulingNumber" placeholder="Scheduling Number" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="gutter">
      <el-col :span="12">
        <el-form-item label="Repeats Number">
          <el-input-number :min="1" v-model="form.repeatsNumber" placeholder="Repeats Number" class="full-width" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="Cron String">
          <el-input v-model="form.cronString" placeholder="Cron String" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="gutter">
      <el-col :span="12">
        <el-form-item label="Messages To Send">
          <el-input v-model="form.messagesToSend" placeholder="Messages To Send" />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="Header Counter">
          <el-input v-model="form.headerCounter" placeholder="Header Counter" />
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
