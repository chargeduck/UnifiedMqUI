import Vue from 'vue'
import PopWindow from '@/components/PopWindow'
export const openPop = (popComponent, externalData = {}, zIndex) => {
  let Pop = Vue.extend(PopWindow)
  const index = document.getElementsByClassName('popWindow').length
  const data = Object.assign({}, { zIndex: index }, { popData: externalData }, { zIndex })
  Pop = new Pop({
    data,
    components: {
      comp: () => Promise.resolve(require(`@/${popComponent}.vue`).default)
    }
  }).$mount()
  document.body.appendChild(Pop.$el)
  return
}
