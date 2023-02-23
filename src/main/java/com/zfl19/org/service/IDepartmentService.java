package com.zfl19.org.service;

import com.zfl19.basic.query.BaseQuery;
import com.zfl19.basic.query.PageList;
import com.zfl19.org.domain.Department;

import java.util.List;

/**
 * 部门业务层
 */
public interface IDepartmentService {

    // 查询所有
    List<Department> getAll();

    // 分页查询
    PageList<Department> getAllByPage(BaseQuery baseQuery);

    // 根据主键删除数据
    void delete(Long id);

    // 新增和修改的联合方法
    void saveOrUpdate(Department department);

}
