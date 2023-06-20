<template>
  <div class="">
    <div v-show="!isLogin">
      <div style="margin: 80px;"></div>
      <el-col span="10" offset="7" class="border-secondary border border-5 bg-light" >
        <el-form :label-position="labelPosition" label-width="100px" :model="formLabelAlign" class="mt-5">
          <el-form-item label="Username:">
            <el-input v-model="formLabelAlign.username" placeholder="Please enter username" class="w-75"></el-input>
          </el-form-item>
          <el-form-item label="Password:">
            <el-input type="password" v-model="formLabelAlign.password" placeholder="Please enter password" class="w-75"></el-input>
          </el-form-item>
          <el-button type="warning" icon="" @click="login">Login</el-button>
          <el-button type="primary" @click="register">Register</el-button>
          <div class="m-5"></div>
        </el-form>
      </el-col>
    </div>

    <div v-show="isLogin">
      <el-col class="text-center" >
        <h3>
          Dear <span class="text-warning h2">{{ formLabelAlign.username }}</span>,  Welcome to the Student Management System!
        </h3>
      </el-col>
    </div>
  </div>
</template>

<script>
import md5 from "js-md5"
import axios from 'axios'
 export default {
   name:"UserLogin",
   data() {
      return {
        labelPosition: 'right',
        formLabelAlign: {
          username:"", 
          password:"",
        },
        isLogin:false
      };
    },
    methods:{
     async login(){
        //console.log("username:", this.formLabelAlign.username);
        //console.log("password:", md5(this.formLabelAlign.password));
        await axios({
              url: "http://localhost:8080/login",
              method: "POST",
              data:{
                username:this.formLabelAlign.username,
                password:md5(this.formLabelAlign.password)
              }
            }).then(res=>{
              if (res.data=="error") {
                this.$alert("Username or password is incorrect!","Alert Message",{
                   confirmButtonText: 'OK'
                });
              }
              else{
                sessionStorage.setItem("username",res.data.username)
                sessionStorage.setItem("token",res.data.token);
                this.$alert("Login Success! Dear " + sessionStorage.getItem("username") + " Welcome!", "Alert Message", {
                  confirmButtonText: 'OK'
                });
                this.isLogin=true;
              }
            })
      },
      register(){
        this.$router.push({
          name:"register",
        })
      },
    },
    mounted() {
      this.$bus.$on("logout",()=>{
        this.isLogin=false
      })
    },
  }
</script>

<style>

</style>