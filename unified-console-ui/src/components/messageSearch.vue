<script setup>
import { defineOptions, defineModel } from 'vue'

defineOptions({
  name: 'MessageSearch'
})

const searchForm = defineModel('searchForm')
const page = defineModel('page')
const reset = () => {
  searchForm.value = {
    text: '',
    startTime: '',
    endTime: ''
  }
  page.value = {
    pageNum: 1,
    pageSize: 10,
    total: 0
  }
  if (doSearch.value) {
    doSearch.value()
  }
}
const doSearch = defineModel('doSearch')
</script>
<template>
  <el-form :model="searchForm" :inline="true">
    <el-form-item label="Text">
      <el-input
        v-model="searchForm.text"
        placeholder="Search Text"
        clearable
        @change="doSearch"
      />
    </el-form-item>
    <el-form-item label="StartTime">
      <el-date-picker
        v-model="searchForm.startTime"
        type="datetime"
        placeholder="Select Start Time"
        value-format="YYYY-MM-DD HH:mm:ss"
        format="YYYY-MM-DD HH:mm:ss"
        @change="doSearch"
      />
    </el-form-item>
    <el-form-item label="EndTime">
      <el-date-picker
        v-model="searchForm.endTime"
        type="datetime"
        placeholder="Select End Time"
        value-format="YYYY-MM-DD HH:mm:ss"
        format="YYYY-MM-DD HH:mm:ss"
        @change="doSearch"
      />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="doSearch">Search</el-button>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="reset">Reset</el-button>
    </el-form-item>
  </el-form>
</template>
<style></style>
