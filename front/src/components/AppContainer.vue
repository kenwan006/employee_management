<template>
  <div>
    <el-container>
      <el-header style="font-size: 24px;">
        Employee  Management  System
      </el-header>

      <el-container>
        <NavMenu/>
        <!-- <el-aside width="200px">
          <div>
            <Navi/>
          </div>
        </el-aside> -->
        <el-main>
          <!-- <Table/> -->
          <keep-alive>
          
            <router-view></router-view>
          </keep-alive>
          <!-- <router-link :to="{
        name:'nameSpace',
        params:{
        // id:1,
        // name:'Jack'
          page:1
        }
        }"></router-link> -->
        
        </el-main>
        
      </el-container>
        <!-- <el-footer>Footer</el-footer> -->
    </el-container>
  </div>
</template>

<script>
import NavMenu from './NavMenu.vue'
// import Table from './Table'
export default {
    name:'AppContainer',
    components:{
        NavMenu,
        // Table,
    }
}
</script>

<style scoped>
    .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }
  
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 40px;
  }
  
</style>>
    