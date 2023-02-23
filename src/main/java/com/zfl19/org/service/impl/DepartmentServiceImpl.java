package com.zfl19.org.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zfl19.basic.query.BaseQuery;
import com.zfl19.basic.query.PageList;
import com.zfl19.org.domain.Department;
import com.zfl19.org.mapper.DepartmentMapper;
import com.zfl19.org.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public PageList<Department> getAllByPage(BaseQuery baseQuery) {
        // 设置分页参数
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
        // 执行查询操作
        List<Department> departments = departmentMapper.selectAll();
        // 封装分页数据
        PageInfo<Department> departmentPageInfo = new PageInfo<>(departments);
        // 使用自己的工具类返回数据
        PageList<Department> departmentPageList = new PageList<>(departmentPageInfo.getTotal(), departmentPageInfo.getList());
        // 返回数据
        return departmentPageList;
    }

    @Override
    public void delete(Long id) {
        departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void saveOrUpdate(Department department) {
        if (department.getId() == null) {
            departmentMapper.insertSelective(department);
        } else {
            departmentMapper.updateByPrimaryKeySelective(department);
        }
    }
}
