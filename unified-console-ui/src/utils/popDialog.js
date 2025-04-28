import { openPop } from '@/components/PopWindow/script'

/**
 * 弹窗
 * @param component   组件地址
 * @param width       组件宽度
 * @param height      组件高度
 * @param refresh     刷新方法
 * @param params      参数 当前的type 0新增 1编辑 2详情 id:编辑和详情的id
 */
export function popDialog(component, width, height, refresh, params) {
  openPop(component, {
    width,
    height,
    ...params,
    upData() {
      if (refresh !== null) {
        refresh()
      }
    },
    closeData() {
      if (refresh !== null) {
        refresh()
      }
    }
  })
}

/**
 * 自动高度弹窗
 * @param component   组件地址
 * @param width       组件宽度
 * @param refresh     刷新方法
 * @param params      参数 当前的type 0新增 1编辑 2详情 id:编辑和详情的id
 */
export function popAutoHeightDialog(component, width, refresh, params) {
  popDialog(component, width, 'auto', refresh, params)
}

