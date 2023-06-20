<template>
  <div>
    <div style="margin: 40px;"></div>
    <el-col span="10" offset="7" class="border-secondary border border-5 bg-light">

      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm mt-5">
        <el-form-item label="Username" prop="username">
          <el-input v-model="ruleForm.username" placeholder="Enter username" class="w-75"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="pass">
          <el-input type="password" v-model="ruleForm.pass" autocomplete="off" placeholder="Enter password" class="w-75"></el-input>
        </el-form-item>
        <el-form-item label="Confirm password" prop="checkPass">
          <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" placeholder="Enter password once again" class="w-75"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">Submit</el-button>
          <el-button @click="resetForm('ruleForm')">Reset</el-button>
        </el-form-item>
          <!-- <div style="margin: 80px;"></div> -->
      </el-form>

    </el-col>
  </div>
</template>

<script>
import md5 from "js-md5"
import axios from 'axios';

 export default {
   name:"UserLogin",
    data() {
      var checkusername = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('Username can not be empty'));
        }
        // setTimeout(() => {
        if (value.length >18 || value.length<6) {
          callback(new Error('Username length must be 6 ~ 18'));
        } else {
          callback();
          }
        // }, 10);
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('Please enter password'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('Please enter password again'));
        } else if (value !== this.ruleForm.pass) {
          callback(new Error('Two passwords not matched'));
        } else {
          callback();
        }
      };
      return {
        ruleForm: {
          pass: '',
          checkPass: '',
          username: ''
        },
        rules: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          username: [
            { validator: checkusername, trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios({
              url: "http://localhost:8080/register",
              method: "POST",
              data:{
                username:this.ruleForm.username,
                password:md5(this.ruleForm.pass)
              }
            }).then(res=>{
              sessionStorage.setItem("username",res.data.username);
              sessionStorage.setItem("token",res.data.token);
            })
            this.$alert("Register success! Now go login","Alert Message",{
                    confirmButtonText: 'OK'
                })
            this.$router.push({
              name:"login"
            })
          } else {
            console.$alert('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style>

</style>