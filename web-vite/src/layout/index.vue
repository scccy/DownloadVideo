<template>
  <el-container class="layout-container">
    <el-aside width="200px">
      <el-menu
        :default-active="activeMenu"
        class="el-menu-vertical"
        :collapse="isCollapse"
        background-color="#304156"
        text-color="#bfcbd9"
        active-text-color="#409EFF"
      >
        <el-menu-item index="/dashboard">
          <el-icon><Monitor /></el-icon>
          <template #title>下载情况</template>
        </el-menu-item>
        
        <el-menu-item index="/files">
          <el-icon><Document /></el-icon>
          <template #title>文件预览</template>
        </el-menu-item>
        
        <el-menu-item index="/settings">
          <el-icon><Setting /></el-icon>
          <template #title>设置</template>
        </el-menu-item>
      </el-menu>
    </el-aside>
    
    <el-container>
      <el-header>
        <div class="header-left">
          <el-icon 
            class="collapse-btn"
            @click="toggleCollapse"
          >
            <Fold v-if="!isCollapse"/>
            <Expand v-else/>
          </el-icon>
        </div>
        <div class="header-right">
          <el-dropdown>
            <span class="user-info">
              admin <el-icon><ArrowDown /></el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="handleLogout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>
      
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { Monitor, Document, Setting, Fold, Expand, ArrowDown } from '@element-plus/icons-vue'

const route = useRoute()
const router = useRouter()
const isCollapse = ref(false)

const activeMenu = computed(() => route.path)

const toggleCollapse = () => {
  isCollapse.value = !isCollapse.value
}

const handleLogout = async () => {
  // TODO: 调用登出API
  await router.push('/login')
}
</script>

<style lang="scss" scoped>
.layout-container {
  height: 100vh;
  
  .el-aside {
    background-color: #304156;
    
    .el-menu {
      border-right: none;
      height: 100%;
    }
  }
  
  .el-header {
    background-color: #fff;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid #dcdfe6;
    
    .collapse-btn {
      font-size: 20px;
      cursor: pointer;
    }
    
    .user-info {
      cursor: pointer;
      color: #303133;
    }
  }
  
  .el-main {
    background-color: #f0f2f5;
    padding: 20px;
  }
}
</style> 