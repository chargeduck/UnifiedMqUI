<script setup>
import { defineOptions, ref } from 'vue'
import { mqTypeOptions, logoList } from '@/utils/const.js'
import { addMqConnect } from '@/api/mqConnect.js'
import { ElMessage } from 'element-plus'

defineOptions({
  name: 'DashboardIndex'
})
const searchForm = ref({
  title: '',
  mqType: 0,
  host: ''
})
const popForm = ref({
  title: '',
  mqType: null,
  host: '',
  port: '',
  username: '',
  password: ''
})

const popData = ref({
  title: '编辑连接',
  visible: false
})

const addConnect = () => {
  popData.value = {
    title: '创建连接',
    visible: true
  }
}

const createConnect = () => {
  addMqConnect(popForm.value).then(resp => {
    ElMessage.success(`创建成功 ${resp.msg}`)
  })
}

const mqList = ref([

])

</script>
<template>
  <el-form :model="searchForm">
    <el-row :gutter="20">
      <el-col :span="4">
        <el-form-item label="类型" prop="mqType">
          <el-select v-model="searchForm.mqType" placeholder="请选择类型" style="width: 100%">
            <el-option v-for="item in mqTypeOptions" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="4">
        <el-form-item label="标题" prop="title">
          <el-input v-model="searchForm.title" placeholder="请输入标题" class="full-width"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="4">
        <el-form-item label="主机" prop="host">
          <el-input v-model="searchForm.host" placeholder="请输入主机" />
        </el-form-item>
      </el-col>
      <el-col :span="4">
        <el-button
          type="primary"
        >
          查询
        </el-button>
        <el-button
          type="primary"
          @click="addConnect"
        >
          新增
        </el-button>
      </el-col>
    </el-row>
  </el-form>
  <el-row
    :gutter="20"
    v-for="(item, index) in mqList"
    :key="index"
    style="margin: 10px 0"
  >
    <el-col
      :span="8"
      v-for="(mq, mqIndex) in item"
      :key="mqIndex"
      :style="{ backgroundImage: logoList[mq.mqType], backgroundSize: 'cover'}"
    >
      <el-card
      >
        <template #header>
          <div class="card-header">
            <el-row :gutter="5">
              <el-col :span="16">
                <el-image
                  :src="logoList[mq.mqType]"
                  style="height: 20px; margin-right: 5px" />
                {{ mq.title }}
              </el-col>
              <el-col :span="4">
                <el-button
                  type="primary"
                  size="small"
                >
                  详情
                </el-button>
              </el-col>
              <el-col :span="4">
                <el-button
                  type="primary"
                  size="small"
                >
                  编辑
                </el-button>
              </el-col>
            </el-row>
          </div>
        </template>
        <el-descriptions
          class="margin-top"
          :column="2"
          border
          style="margin-top: 5px"
        >
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                MQ
              </div>
            </template>
            {{ mqTypeOptions[mq.mqType].label }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                Version
              </div>
            </template>
            {{ mq.version }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                Host
              </div>
            </template>
            {{ mq.host }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                Port
              </div>
            </template>
            {{ mq.port }}
          </el-descriptions-item>

          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                Cluster
              </div>
            </template>
            {{ mq.cluster || false }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                Status
              </div>
            </template>
            {{ mq.status || 'Ready' }}
          </el-descriptions-item>
        </el-descriptions>
      </el-card>
    </el-col>
  </el-row>
  <el-pagination
    background
    layout="total, prev, pager, next"
    :total="100" />
  <el-dialog :title="popData.title" width="60%" v-model="popData.visible">
    <el-form :model="popForm" style="margin: 20px" label-width="80" label-position="left">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="Title" prop="title">
            <el-input v-model="popForm.title" placeholder="请输入标题" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="MQ" prop="mqType">
            <el-select v-model="popForm.mqType" placeholder="请选择类型" style="width: 100%">
              <el-option v-for="item in mqTypeOptions" :key="item.value" :label="item.label" :value="item.value" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="Host" prop="host">
            <el-input v-model="popForm.host" placeholder="请输入主机" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Port" prop="port">
            <el-input-number :min="0" :max="65535" v-model="popForm.port" placeholder="请输入端口号" class="full-width"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="Username" prop="username">
            <el-input v-model="popForm.username" placeholder="请输入账号" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Password" prop="password">
            <el-input v-model="popForm.password" type="password" placeholder="请输入密码"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item>
        <el-button type="primary" @click="popData.visible = false">
          取消
        </el-button>
        <el-button type="primary" @click="createConnect">
          确定
        </el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
<style scoped>
.full-width {
  width: 100%;
}
</style>
