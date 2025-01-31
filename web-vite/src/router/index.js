import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/login',
    component: () => import('@/views/login/index.vue'),
    hidden: true
  },
  {
    path: '/',
    component: () => import('@/layout/index.vue'),
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index.vue'),
        name: 'Dashboard',
        meta: { title: '下载情况' }
      },
      {
        path: 'files',
        component: () => import('@/views/files/index.vue'),
        name: 'Files',
        meta: { title: '文件预览' }
      },
      {
        path: 'settings',
        component: () => import('@/views/settings/index.vue'),
        name: 'Settings',
        meta: { title: '设置' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router 