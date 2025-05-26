<script setup>
import { defineOptions, ref } from 'vue'
import { mqTypeOptions, logoList } from '@/utils/const.js'
import { addMqConnect, delById, fetchConnectList, getConnectById } from '@/api/mqConnect.js'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Hide, Lock, User, View } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'
import { commonQuery } from '@/utils/commonQuery.js'
import { allMqPort } from '@/api/mqPortDict.js'
import { isBlank } from '@/utils/format.js'

defineOptions({
  name: 'DashboardIndex'
})
const router = useRouter()
const searchForm = ref({
  title: '',
  mqType: null,
  host: ''
})
const page = ref({
  current: 1,
  size: 6,
  total: 0
})
const popForm = ref({
  title: '',
  mqType: '',
  host: '',
  port: '',
  username: '',
  password: '',
  inputPorts: []
})
const popFormRef = ref()
const popFormRules = {
  mqType: [
    { required: true, message: '请选择类型', trigger: 'change' }
  ],
  host: [
    { required: true, message: '请输入主机地址', trigger: 'blur' }
  ],
  port: [
    { required: true, message: '请输入端口号', trigger: 'blur' }
  ]
}
const popData = ref({
  title: '编辑连接',
  visible: false
})
const mqList = ref([])
const loadFlag = ref(false)
const mqPortDict = ref([])
const choosePortDict = ref([])
// 新增链接按钮时间
const addBtnFunction = () => {
  popData.value = {
    title: '创建连接',
    visible: true,
    showBtn: true
  }
}

const createConnect = async () => {
  await popFormRef.value.validate()
  loadFlag.value = true
  popForm.value.inputPorts = choosePortDict.value
  const data = commonQuery(popForm.value, null)
  await addMqConnect(data).then(resp => {
    ElMessage.success(`创建成功 ${ resp.msg }`)
  }).finally(() => {
    loadFlag.value = false
    popData.value.visible = false
    resetForm()
    fetchList()
  })
}

const cancelDialog = () => {
  popData.value.visible = false
  resetForm()
}
const resetForm = () => {
  popForm.value = {
    title: '',
    mqType: null,
    host: '',
    port: '',
    username: '',
    password: ''
  }
}
const fetchList = () => {
  fetchConnectList({ params: searchForm.value, page: page.value }).then(resp => {
    mqList.value = resp.data.records
    page.value.total = resp.data.total
    page.value.current = resp.data.current
    page.value.size = resp.data.size

  })
}
const editConnect = (id) => {
  popData.value = {
    title: '编辑连接',
    visible: true,
    showBtn: true
  }
  connectDetail(id)
}
const showConnectDetail = (id) => {
  router.push({
    name: 'activeMQ',
    query: { id }
  })
}

const onSizeChange = (size) => {
  // console.log('当前每页条数', size)
  // 只要是每页条数变化了，那么原本正在访问的当前页意义不大了，数据大概率已经不在原来那一页了
  // 重新从第一页渲染即可
  page.value.current = 1
  page.value.size = size
  // 基于最新的当前页 和 每页条数，渲染数据
  fetchList()
}
const onCurrentChange = (current) => {
  // 更新当前页
  page.value.current = current
  // 基于最新的当前页，渲染数据
  fetchList()
}

const connectDetail = (id) => {
  getConnectById(id).then(resp => {
    popForm.value = resp.data
  })
}
const delConnect = (id) => {
  ElMessageBox.confirm('确认删除此连接?', 'Warning', {
    confirmButtonText: 'OK',
    cancelButtonText: 'Cancel',
    type: 'warning'
  }).then(() => {
    delById(id).then(resp => {
      ElMessage.success(`删除成功${ resp.msg }`)
    }).finally(() => {
      fetchList()
    })
  })
}
const pwdInputType = ref('password')
const pwdIconTrigger = () => {
  pwdInputType.value = pwdInputType.value === 'password' ? 'text' : 'password'
}
const fetchAllMqPort = () => {
  allMqPort().then(resp => {
    mqPortDict.value = resp.data
  })
}

const portDictFilter = () => {
  const type = popForm.value.mqType
  if (!isBlank(type)) {
    if (mqPortDict.value.length === 0) {
      fetchAllMqPort()
    }
    choosePortDict.value = mqPortDict.value.filter(item => item.mqType === type)
    const mqDefaultDict = choosePortDict.value.find(item => item.defaultFlag)
    if (mqDefaultDict) {
      popForm.value.port = mqDefaultDict.defaultPort
    }
  }
}
fetchAllMqPort()
fetchList()

</script>
<template>
  <el-form :model="searchForm">
    <el-row :gutter="20">
      <el-col :span="4">
        <el-form-item label="类型" prop="mqType">
          <el-select v-model="searchForm.mqType" placeholder="请选择类型" style="width: 100%" clearable filterable>
            <el-option v-for="item in mqTypeOptions" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="4">
        <el-form-item label="标题" prop="title">
          <el-input v-model="searchForm.title" placeholder="请输入标题" class="full-width" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="4">
        <el-form-item label="主机" prop="host">
          <el-input v-model="searchForm.host" placeholder="请输入主机" clearable />
        </el-form-item>
      </el-col>
      <el-col :span="4">
        <el-button
          type="primary"
          @click="fetchList"
        >
          查询
        </el-button>
        <el-button
          type="primary"
          @click="addBtnFunction"
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
              <el-col :span="15">
                <el-image
                  :src="logoList[mq.mqType]"
                  style="height: 20px; margin-right: 5px" />
                {{ mq.title }}
              </el-col>
              <el-col :span="3">
                <el-button
                  type="primary"
                  size="small"
                  @click="showConnectDetail(mq.id)"
                >
                  详情
                </el-button>
              </el-col>
              <el-col :span="3">
                <el-button
                  type="primary"
                  size="small"
                  @click="editConnect(mq.id)"
                >
                  编辑
                </el-button>
              </el-col>
              <el-col :span="3">
                <el-button
                  type="danger"
                  size="small"
                  @click="delConnect(mq.id)"
                >
                  删除
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
                Active
              </div>
            </template>
            <el-switch disabled v-model="mq.activeFlag"
                       style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949" />
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                BrokerId
              </div>
            </template>
            {{ mq.brokerId }}
          </el-descriptions-item>
        </el-descriptions>
      </el-card>
    </el-col>
  </el-row>
  <!-- 分页区域 -->
  <el-pagination
    v-model:current-page="page.current"
    v-model:page-size="page.size"
    :page-sizes="[3,6,9,12]"
    :background="true"
    layout="jumper, total, sizes, prev, pager, next"
    :total="page.total"
    @size-change="onSizeChange"
    @current-change="onCurrentChange"
    style="margin-top: 20px; justify-content: flex-end"
  />
  <el-dialog :title="popData.title" width="60%" v-model="popData.visible">
    <el-form v-loading="loadFlag" :model="popForm" ref="popFormRef" :rules="popFormRules" style="margin: 20px"
             label-width="80" label-position="left">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="Title" prop="title">
            <el-input v-model="popForm.title" placeholder="请输入标题" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="MQ" prop="mqType">
            <el-select
              v-model="popForm.mqType"
              @change="portDictFilter"
              clearable
              placeholder="请选择类型" style="width: 100%">
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
            <el-input-number :min="0" :max="65535" v-model="popForm.port" placeholder="请输入端口号"
                             class="full-width" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="Username" prop="username">
            <el-input :prefix-icon="User" v-model="popForm.username" placeholder="请输入账号" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Password" prop="password">
            <el-input
              :prefix-icon="Lock"
              v-model="popForm.password"
              :type="pwdInputType"
              placeholder="请输入密码">
              <template #append>
                <el-icon @click="pwdIconTrigger">
                  <component :is="pwdInputType === 'password' ? View : Hide" />
                </el-icon>
              </template>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col>
          <el-form-item label="Extra" prop="extra">
            <el-input
              type="textarea"
              v-model="popForm.extra"
              placeholder="请输入额外信息"
              :autosize="{minRows: 3, maxRows: 5}"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20" style="margin: 10px">
        <el-table :data="choosePortDict" border stripe>
          <el-table-column prop="portLabel" label="默认标题" />
          <el-table-column prop="defaultPort" label="默认端口" />
          <el-table-column prop="defaultFlag" label="是否默认" />
          <el-table-column prop="inputPort" label="自定义端口">
            <template #default="scope">
              <el-input-number
                v-if="scope.row.defaultFlag"
                v-model="popForm.port"
                disabled
                class="full-width" />
              <el-input-number
                v-else
                :min="0"
                :max="65535"
                v-model="scope.row.inputPort"
                placeholder="请输入端口号"
                class="full-width" />
            </template>
          </el-table-column>
          <el-table-column prop="desc" label="描述" />
        </el-table>
      </el-row>
      <el-form-item v-show="popData.showBtn">
        <el-button type="primary" @click="cancelDialog">
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
