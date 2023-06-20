<template>
  <div>
  <el-col >
    <el-menu
      :default-active="activatedItem"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose">
      <el-menu-item index="user" @click="toUser" id="login">
        <i class="el-icon-user-solid" ></i>
        <span slot="title">Login/Register</span>
      </el-menu-item>

      <el-menu-item index="students" @click="toEmployeesList">
        <i class="el-icon-document" ></i>
        <i class="bi bi-person-lines-fill"></i>
        <span slot="title">Employee List</span>
      </el-menu-item>

      <el-menu-item index="analysis" @click="toAnalysis">
        <i class="el-icon-s-management"></i>
        <span slot="title">Data Analytics</span>
      </el-menu-item>

      <el-menu-item index="logout" @click="toLogout">
        <i class="el-icon-setting"></i>
        <span slot="title">Logout</span>
      </el-menu-item>

      <br><br><br><br><br>
      <br><br><br><br><br>
      <br>
    </el-menu>
  </el-col>
  </div>
</template>

<script>
export default {
    name:'NavMenu',
    data(){
      return {
        activatedItem:this.$route.name
      }
    },
    methods:{
     toUser(){
        
        this.$router.push({
          // path:"/user"
          name:"login"
        })
      },
     async toEmployeesList(){
        var token= sessionStorage.getItem("token");//once loggin the token will be saved at the sessionStorage
        if (token===null) {
          await this.$alert('Please login first!', 'Alert Message', {confirmButtonText: 'OK',});
          // this.$refs["login"].click()
          document.getElementById("login").click();
        }else{
          this.$router.push({
            name:"employees"
            // path:"/employees"
          })
        }
      },
      toLogout(){
        sessionStorage.clear();
        this.$router.push({
          name:"logout"
        })
        this.$bus.$emit('logout');
      },
      toAnalysis() {
        this.$router.push({
          name:"analysis"
        })
      }
    },
    mounted() {
        if(this.$route.name=="register"||this.$route.name=="login"){
          this.activatedItem="user"
        }
        else if(this.$route.name=="logout"){
          this.activatedItem="logout"
        }
        else if(this.$route.name=="analysis"){
          this.activatedItem="analysis"
        }
    },
    
}
</script>

<style scoped>
  div{
    width: 200px
  }
</style>