package com.zfl19.org.service;

import com.zfl19.basic.query.BaseQuery;
import com.zfl19.basic.query.PageList;
import com.zfl19.org.domain.Employee;

import java.util.List;

/**
 * 员工业务层
 */
public interface IEmployeeService {

    // 查询所有
    List<Employee> getAll();

    // 分页查询
    PageList<Employee> getAllByPage(BaseQuery baseQuery);

    // 根据主键删除数据
    void delete(Long id);

    // 新增和修改的联合方法
    void saveOrUpdate(Employee employee);

    // 查询所有的部门经理
    List<Employee> getAllManager();

}
