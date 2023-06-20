package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Employee;

import java.util.HashMap;
import java.util.List;

public interface EmployeeMapper extends BaseMapper<Employee> {
    @Select("select avg(yoe) yoe,avg(salary) salary,avg(pto) pto from employee;")
    public HashMap<String,Long> getAvg();
    @Select("select position name,count(position) value from employee group by position;")
    public List<HashMap<String,Integer>> getPositionCount();
}
