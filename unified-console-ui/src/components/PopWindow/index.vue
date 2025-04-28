<template>
  <div
    v-if="visible"
    ref="popWindow"
    class="popWindow"
    :style="`z-index:200${zIndex}`"
  >
    <div
      class="wrap-container"
      :style="`width:${popData && popData.width};height:${
        popData && popData.height
      };`"
    >
      <div v-if="showClose" class="close" @click="closePop">
        <i class="el-icon-close" />
      </div>
      <div class="content">
        <comp :pop-data="popData" :close-pop="closePop" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PopWindow',
  components: {
    comp: () => Promise.resolve(require(`@/${visible}.vue`).default)
  },
  data() {
    return {
      visible: true,
      zIndex: 1,
      popData: {}
    }
  },
  computed: {
    showClose() {
      return !this.popData.hideClose
    }
  },
  methods: {
    closePop() {
      this.visible = false
      if (this.popData.closeData) {
        this.popData.closeData()
      }
      this.$nextTick(() => {
        this.$destroy()
      })
    }
  }
}
</script>

<style lang="scss">
.popWindow {
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 2001;
  background-color: rgba($color: black, $alpha: 0.2);
  display: flex;
  justify-content: center;
  align-items: center;
  .wrap-container {
    // max-width: 1632px;
    max-height: 918px;
    background-color: #fff;
    border: 1px solid #fff;
    position: relative;
    box-shadow: 0px 0px 30px 0px rgba(0, 0, 0, 0.15);
    border-radius: 5px;
    // padding: 0 10px;
    .close {
      position: absolute;
      right: 30px;
      top: 18px;
      width: 16px;
      height: 16px;
      text-align: center;
      line-height: 16px;
      cursor: pointer;
      z-index: 201;
      font-size: 24px;
      color: #666;
    }
    > .content {
      width: 100%;
      height: 100%;
      padding: 0;
      position: relative;
      z-index: 200;
      display: flex;
      flex-direction: column;
      flex: 1;
    }
  }
}
</style>
