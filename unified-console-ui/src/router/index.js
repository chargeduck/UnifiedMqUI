import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/views/layout/index.vue'


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
          component: () => import('@/views/dashboard/Dashboard.vue')
        },
        {
          path: '/activemq/index',
          name: 'activeMQ',
          component: () => import('@/views/activemq/index.vue')
        }
      ]
    }
  ]
})
router.beforeEach((to, from, next) => {
  next()
})
export default router
