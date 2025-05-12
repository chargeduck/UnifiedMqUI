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
        },
        {
          path: '/kafka/index',
          name: 'kafka',
          component: () => import('@/views/kafka/index.vue')
        },
        {
          path: '/rabbitmq/index',
          name: 'rabbitMQ',
          component: () => import('@/views/rabbitmq/index.vue')
        },
        {
          path: '/rocketmq/index',
          name: 'rocketMQ',
          component: () => import('@/views/rocketmq/index.vue')
        },
        {
          path: '/mqtt/index',
          name: 'mqtt',
          component: () => import('@/views/mqtt/index.vue')
        }
      ]
    }
  ]
})
router.beforeEach((to, from, next) => {
  next()
})
export default router
