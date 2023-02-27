package com.zfl19.org.mapper;

import com.zfl19.org.domain.Employee;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface EmployeeMapper extends Mapper<Employee> {

    // 查询所有部门
    List<Employee> getAllManager();

}
