import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/views/layout/index.vue'
// import { useUserInfoStore } from '@/stores/userInfo'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: Layout,
      children: [
        {
          path: '/dashboard',
          name: 'dashboard',
          component: () => import('@/views/dashboard/Dashboard.vue'),
        },
        {
          path: '/article/manage',
          name: 'articleManage',
          component: () => import('@/views/article/ArticleManager.vue'),
        },
        {
          path: '/article/channel',
          name: 'articleChannel',
          component: () => import('@/views/article/ArticleChannel.vue'),
        },
        {
          path: '/user/profile',
          name: 'userProfile',
          component: () => import('@/views/user/UserProfile.vue'),
        },
        {
          path: '/user/avatar',
          name: 'userAvatar',
          component: () => import('@/views/user/UserAvatar.vue'),
        },
        {
          path: '/user/password',
          name: 'userPassword',
          component: () => import('@/views/user/UserPassword.vue'),
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
