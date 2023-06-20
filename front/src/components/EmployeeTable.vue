<template>
  <div>
    <table class="table caption-top  table-hover">
        <caption>
            <span class="h5 text-dark">Employee List</span>
            <span class="col-4 offset-3">
                <el-input class="w-25" placeholder="Please Enter Employee's Name" v-model="searchName"></el-input>
                <el-button type="primary" icon="el-icon-search" circle class="m-1" @click="searchEmployee" v-show="!isSearched"></el-button>
                <el-button type="info" icon="el-icon-refresh-right" circle class="m-1" @click="backBeforeSearch" v-show="isSearched"></el-button>
            </span>
            <span class="col-1">
                <el-button type="warning" icon="el-icon-plus" circle @click="centerDialogVisible = true"></el-button>
                <!-- <el-button type="text" >点击打开 Dialog</el-button> -->

                    <el-dialog
                    title="Add Employee"
                    :visible.sync="centerDialogVisible"
                    width="35%"
                    center>
                    <div class="text-center">
                        Employee number:
                    <el-input v-model="newEmployee.number" class="w-50" placeholder="Enter Number"></el-input>
                    </div>
                    <div class="mt-1 text-center">
                        Employee Name:
                    <el-input v-model="newEmployee.name" class="w-50" placeholder="Enter Name"></el-input>
                    </div>
                    <div class="mt-1 text-center">
                        Employee Age:
                    <el-input v-model.number="newEmployee.age" class="w-50" placeholder="Enter Age" type="text"></el-input>
                    </div>
                    <div class="mt-1 text-center">
                        Employee  position:
                    <el-input v-model="newEmployee.position" class="w-50" placeholder="Enter Position"></el-input>
                    </div>
                    <div class="mt-1 text-center">
                        Employee department:
                    <el-input v-model="newEmployee.department" class="w-50" placeholder="Enter Department"></el-input>
                    </div>
                    <div class="mt-1 text-center">
                        Year of experience:
                    <el-input v-model.number="newEmployee.yoe" class="w-50" placeholder="Enter YOE" type="text"></el-input>
                    </div>
                    <div class="mt-1 text-center">
                        Salary:
                    <el-input v-model.number="newEmployee.salary" class="w-50" placeholder="Enter Salary" type="text"></el-input>
                    </div>
                    <div class="mt-1 text-center">
                        PTO:
                    <el-input v-model.number="newEmployee.pto" class="w-50" placeholder="Enter PTO" type="text"></el-input>
                    </div>
                    <span slot="footer" class="dialog-footer">
                        <el-button @click="centerDialogVisible = false">Cancel</el-button>
                        <el-button type="primary" @click="addEmployee">Add</el-button>
                    </span>
                    </el-dialog>
                <!-- <el-button type="warning" icon="el-icon-plus" circle @click="addStudent"></el-button> -->
            </span>
        
        </caption>
        <thead>
            <tr>
            <th scope="col">Number</th>
            <th scope="col">Name</th>
            <th scope="col">Age</th>
            <th scope="col">Position</th>
            <th scope="col">Department</th>
            <th scope="col">YOE</th>
            <th scope="col">Salary</th>
            <th scope="col">PTO</th>
            <th scope="col">Action</th>
            </tr>
        </thead>
        <tbody>
            <EmployeeItem v-for="emp in employees" :key="emp.id" :employeeCopy="emp" v-on:reloadTable="updateTable"/>
        </tbody>
    </table>
    <el-pagination
    v-show="!isSearched"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
    :current-page="currentPage4"
    :page-sizes="[5,8,10]"
    :page-size="5"
    layout="total, sizes, prev, pager, next, jumper"
    :total="count"
    :i18n="{
        total: 'Total',
        sizes: 'Page Sizes',
        itemsPerPage: 'Items per page',
        currentPage: 'Current Page',
        goto: 'Go to',
        pageClassifier: ''
    }"
    ></el-pagination>
        <!-- <el-button-group v-show="!isSearched">
            <el-button type="primary" icon="el-icon-arrow-left" @click="lastPage">上一页</el-button>
            <el-input placeholder="页数"></el-input>
            <el-button type="primary" @click="nextPage">下一页<i class="el-icon-arrow-right el-icon--right"></i></el-button>
        </el-button-group> -->
  </div>
</template>

<script>
import axios from 'axios'
import EmployeeItem from "./EmployeeItem"
// import VNode from "./VNode"
export default {
    name:'EmpoyeeTable',
    data() {
        return {
            centerDialogVisible: false,
            employees:[],
            page:1,
            count: 0,
            number_per_page:5,
            newEmployee:{
                number:"", name:"", age:"", position:"", department:"", yoe:"", salary:"", pto:"",
            },
            searchName: "",
            
            isSearched:false,
        }
    },
  
    methods: {
        handleSizeChange(val) {
            console.log(`Each page has ${val} items`);
            this.number_per_page = val;
            axios({
              url: "http://localhost:8080/employees",
              method: "POST", 
              data: {
                  page: this.page,
                  number_per_page: this.number_per_page
              }
            }).then(res => {
            this.employees=res.data
           })
      },
      handleCurrentChange(val) {
          console.log(`Current: ${val}`);
          this.page = val
          axios({
              url: "http://localhost:8080/employees",
              method: "POST", 
              data: {
                  page: this.page,
                  number_per_page: this.number_per_page
              }
          }).then(res => {
            this.employees=res.data
           })

      },

      /** 
        nextPage() {
            if(this.page*5>=this.count){
                 this.$alert('没有下一页啦', '温馨提示', {
                confirmButtonText: '知道啦',
                });
            }
            else{
                this.page+=1;
                axios.get('http://localhost:8080/students/'+this.page).then(
                    response => {
                        this.students=response.data;
                    },
                    error =>{
                        console.log(error.massage);
                    }
                )
            }
            
        },
        lastPage(){
            if(this.page===1){
                this.$alert('这已经是第一页啦', '温馨提示', {
                confirmButtonText: '知道啦',
                });
            }
            else{
                this.page-=1;
                axios.get('http://localhost:8080/students/'+this.page).then(
                response => {
                    this.students=response.data;
                },
                error =>{
                    console.log(error.massage);
                }
            )
            }
        },
        **/
        async  addEmployee(){
            if (this.newEmployee.number==""||this.newEmployee.name==""||this.newEmployee.age===""||this.newEmployee.position===""||this.newEmployee.department==="" || this.newEmployee.salary==="" || this.newEmployee.pto==="") {
                this.$alert('Can not be empty!', 'Alert Message', {
                 confirmButtonText: 'OK',
                });
            }
            else if (this.newEmployee.age<=0 || this.newEmployee.age>100) {
                 this.$alert('It is not a valid age', 'Alert Message', {
                 confirmButtonText: 'OK',
                });
            }
            else if(this.newEmployee.salary<50000 || this.newEmployee.chi>300000){
                this.$alert('Grade has to be 50K ~ 300K', 'Alert Message', {
                 confirmButtonText: 'OK',
                });
            }
            else if(this.newEmployee.pto<0 || this.newEmployee.pto>30){
                this.$alert('PTO has to be 0 ~ 30', 'Alert Message', {
                 confirmButtonText: 'OK',
                });
            }else if(this.newEmployee.yoe<0 || this.newEmployee.yoe>100){
                this.$alert('YOE has to be 0 ~ 100', 'Alert Message', {
                 confirmButtonText: 'OK',
                });
            } 
            else{
               await axios({
                    url:"http://localhost:8080/add/",
                    method:"POST",
                    data:this.newEmployee
                });
                this.$message({
                type: 'success',
                message: 'Add Success!'
            });
                this.centerDialogVisible=false;
             await  axios.get('http://localhost:8080/get_employees_count/').then(
             response => {
                    this.count=parseInt(response.data);
                },
                error =>{
                    console.log(error.massage);
                })
               await this.updateTable();
            }
        },
        searchEmployee(){
            
            // alert('http://localhost:8080/search?name='+this.searchName+"&")
            if (this.searchName==="") {
                this.$alert("Search content can not be empty","Alert Message",{
                    confirmButtonText: 'OK'
                })
            }
            else{
                axios({
                    url:"http://localhost:8080/search",
                    method:"POST",
                    data:{
                        name:this.searchName,
                    }
                }).then(
                    response => {
                        console.log(response.data);
                        // this.students=response.data.students;
                        this.employees=response.data;
                        // alert(this.count);
                    },
                    error =>{
                        console.log(error.massage);
                    }
                )
                
                this.isSearched=true;
            }
        },
       backBeforeSearch(){
            this.searchName=""
            // axios.get('http://localhost:8080/students/'+this.page).then(
            //     response => {
            //         console.log(response.data);
            //         // this.students=response.data.students;
            //         this.students=response.data;
            //     },
            //     error =>{
            //         console.log(error.massage);
            //     }
            // );
            this.updateTable()
            this.isSearched=false;
        },
      async  updateTable() {
            // alert("表格更新")
          axios({
              url: "http://localhost:8080/employees",
              method: "POST", 
              data: {
                  page: this.page,
                  number_per_page: this.number_per_page
              }
            }).then(res => {
            this.employees=res.data;
            })
            axios.get('http://localhost:8080/get_employees_count/').then(
                response => {
                    this.count=parseInt(response.data);
                },
                error =>{
                    console.log(error.massage);
                })
        },

    },
    components:{
        EmployeeItem
    },
    mounted() {
        axios({
              url: "http://localhost:8080/employees",
              method: "POST", 
              data: {
                  page: this.page,
                  number_per_page: this.number_per_page
              }
            }).then(res => {
                this.employees=res.data;
                console.log(this.employees);
            })
        axios.get('http://localhost:8080/get_employees_count/').then(
             response => {
                    this.count=parseInt(response.data);
                },
                error =>{
                    console.log(error.massage);
                })
    },
   async beforeRouteEnter (to, from, next) {
       if(sessionStorage.getItem('token')==null){
            alert("Please login first!")
       }else{
            await axios({
                url: "http://localhost:8080/checkToken",
                method: "POST",
                data: {
                    username: sessionStorage.getItem('username'),
                    token: sessionStorage.getItem('token')
                }
            }).then(res => {
                if (res.data=="1") {
                    next()
                }
                else{
                    alert("Login expired. Pleae login again")
                    location.reload();
                }
            })
       }
       
        // next()
    }
    

}
</script>

<style scoped>

</style>