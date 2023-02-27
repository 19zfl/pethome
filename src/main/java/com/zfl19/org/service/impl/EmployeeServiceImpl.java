package com.zfl19.org.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zfl19.basic.query.BaseQuery;
import com.zfl19.basic.query.PageList;
import com.zfl19.org.domain.Employee;
import com.zfl19.org.mapper.EmployeeMapper;
import com.zfl19.org.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public PageList<Employee> getAllByPage(BaseQuery baseQuery) {
        // 设置分页参数
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
        // 执行查询操作
        List<Employee> employees = employeeMapper.selectAll();
        // 封装分页数据
        PageInfo<Employee> employeePageInfo = new PageInfo<>(employees);
        // 使用自己的工具类返回数据
        PageList<Employee> employeePageList = new PageList<>(employeePageInfo.getTotal(), employeePageInfo.getList());
        // 返回数据
        return employeePageList;
    }

    @Override
    public void delete(Long id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void saveOrUpdate(Employee employee) {
        if (employee.getId() == null) {
            employeeMapper.insertSelective(employee);
        } else {
            employeeMapper.updateByPrimaryKeySelective(employee);
        }
    }

    @Override
    public List<Employee> getAllManager() {
        return employeeMapper.getAllManager();
    }

}
