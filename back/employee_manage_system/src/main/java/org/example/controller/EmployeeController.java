package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import org.example.mapper.EmployeeMapper;
import org.example.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Api(tags = "学生信息接口")
@CrossOrigin("*")
@SuppressWarnings("all")
@RestController
public class EmployeeController {
    Gson gson = new Gson();
    @Autowired
    private EmployeeMapper employeeMapper;

    // 分页查询学生
    @PostMapping("/employees")
    public String getStudent(@RequestBody HashMap<String, Integer> data) {
        int page = data.get("page");
        int numberPerPage = data.get("number_per_page");
        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        employeeQueryWrapper.last(String.format("limit %s,%s", page * numberPerPage - numberPerPage, numberPerPage));
        List<Employee> employees = employeeMapper.selectList(employeeQueryWrapper);
        return gson.toJson(employees);
    }

    // 获取学生总数
    @GetMapping("/get_employees_count")
    public long getEmployeeCount() {
        Long count = employeeMapper.selectCount(null);
        return count;
    }

    // 添加学生
    @PostMapping("/add")
    public void addEmployee(@RequestBody Employee employee) {
        employeeMapper.insert(employee);
    }

    // 删除学生
    @PostMapping("/delete")
    public void deleteEmployee(@RequestBody Employee employee) {
        employeeMapper.deleteById(employee);
    }

    // 修改学生信息
    @PostMapping("/update")
    public void updateEmployee(@RequestBody Employee employee) {
        employeeMapper.updateById(employee);
    }

    // 根据姓名模糊查询学生
    @PostMapping("/search")
    public String searchEmployee(@RequestBody Employee employee) {
        String name = employee.getName();
        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        employeeQueryWrapper.like("name", name);
        List<Employee> employees = employeeMapper.selectList(employeeQueryWrapper);
        return gson.toJson(employees);
    }


}
