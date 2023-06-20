package org.example;

import org.example.mapper.EmployeeMapper;
import org.example.mapper.UserMapper;
import org.example.pojo.Employee;
import org.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
public class Test {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @org.junit.jupiter.api.Test
    public void testUser(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::print);
    }
    public void testEmployee(){
        List<Employee> employees = employeeMapper.selectList(null);
        employees.forEach(System.out::print);
    }
}
