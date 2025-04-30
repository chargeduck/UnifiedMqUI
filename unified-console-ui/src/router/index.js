import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/views/layout/index.vue'
// import { useUserInfoStore } from '@/stores/userInfo'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: Layout,
      redirect: '/dashboard',
      children: [
        {
          path: '/dashboard',
          name: 'dashboard',
          component: () => import('@/views/dashboard/Dashboard.vue'),
        },
        {
          path: '/activemq/index',
          name: 'activeMQ',
          component: () => import('@/views/activemq/index.vue'),
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/login/index.vue'),
    }
  ],
})
router.beforeEach((to, from, next) => {
  next()
  // const userInfoStore = useUserInfoStore()
  // const token = userInfoStore.token
  // if (to.path === '/login') {
  //   next()
  // } else {
  //   if (token) {
  //     next()
  //   } else {
  //     next('/login')
  //   }
  // }
})
export default router
