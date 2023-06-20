<template>
    <tr>
      <td>{{ employee.number }}</td>
      <td v-if="!isEdited">{{ employee.name }}</td>
      <td v-if="!isEdited">{{ employee.age }}</td>
      <td v-if="!isEdited">{{ employee.position }}</td>
      <td v-if="!isEdited">{{ employee.department }}</td>
      <td v-if="!isEdited">{{ employee.yoe }}</td>
      <td v-if="!isEdited">{{ employee.salary }}</td>
      <td v-if="!isEdited">{{ employee.pto }}</td>
      <td v-if="!isEdited">
        <el-button type="primary" icon="el-icon-edit" circle @click="edit"></el-button>
        <el-button type="danger" icon="el-icon-delete" circle @click="handleDelete"></el-button>
      </td>
      <td v-if="isEdited">
        <el-input type="text" v-model.number="employee.name" class="w-75"></el-input>
      </td>
      <td v-if="isEdited">
        <el-input type="text" v-model.number="employee.age" class="w-50"></el-input>
      </td>
      <td v-if="isEdited">
        <el-input type="text" v-model="employee.position" class="w-75"></el-input>
      </td>
      <td v-if="isEdited">
        <el-input type="text" v-model="employee.department" class="w-75"></el-input>
      </td>
      <td v-if="isEdited">
        <el-input type="text" v-model.number="employee.yoe" class="w-50"></el-input>
      </td>
      <td v-if="isEdited">
        <el-input type="text" v-model.number="employee.salary" class="w-50"></el-input>
      </td>
      <td v-if="isEdited">
        <el-input type="text" v-model.number="employee.pto" class="w-50"></el-input>
      </td>
      <td v-if="isEdited">
        <el-button type="success" icon="el-icon-check" circle @click="save"></el-button>
        <el-button type="danger" icon="el-icon-delete" circle @click="handleDelete"></el-button>
      </td>
    </tr>
  </template>
  
  <script>
  import axios from 'axios'
  
  export default {
    name: 'EmployeeItem',
    props: ['employeeCopy'],
    data() {
      return {
        isEdited: false,
        employee: { ...this.employeeCopy }
      }
    },
    methods: {
        edit(){
            this.isEdited=true;
            console.log(this.$route.params);
        },
        async save() {
            const updatedEmployee = Object.assign({}, this.employee);
            if (updatedEmployee.age > 100 || updatedEmployee.age < 0) {
                this.$alert('Illegal Ange', 'Alert Messge', {
                    confirmButtonText: 'OK',
                });
            } else if (updatedEmployee.yoe > 100 || updatedEmployee.yoe < 0) {
                this.$alert('Year of experience has to be 0 ~100！', 'Alert Message', {
                    confirmButtonText: 'OK',
                });
            } else if (updatedEmployee.salary > 300000 || updatedEmployee.salary < 50000) {
                this.$alert('Salary has to be 50K ~ 300K！', 'Alert Message', {
                    confirmButtonText: 'OK',
                });
            } else if (updatedEmployee.pto > 50 || updatedEmployee.eng < 0) {
                this.$alert('PTO has to be 0 ~50', 'Alert Message', {
                    confirmButtonText: 'OK',
                });
            } else {
                await axios({
                    url: "http://localhost:8080/update",
                    method: "POST",
                    data: updatedEmployee
                })
                this.isEdited = false;
            }
        },

        handleDelete(){
          this.$confirm('File to be deleted permanently, continue?', 'Alert Message', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(async () => {
          await  axios({
                url:"http://localhost:8080/delete/",
                method: 'POST',
                data:{
                    id:this.employee.id
                }
            })
            this.$emit('reloadTable');
            this.$message({
                type: 'success',
                message: 'Delete Success!'
            });
            }).catch(() => {
            this.$message({
                type: 'info',
                message: 'Delete cancelled'
            });          
            });
        }
    },
}
</script>

<style scoped>
    input{
        height:30px
    }
    td{
        width:60px
    }
</style>