package com.zfl19.org.service;

import com.zfl19.basic.query.BaseQuery;
import com.zfl19.basic.query.PageList;
import com.zfl19.basic.service.IBaseService;
import com.zfl19.org.domain.Department;
import com.zfl19.org.query.DepartmentQuery;

import java.util.List;

/**
 * 部门业务层
 */
public interface IDepartmentService extends IBaseService<Department, BaseQuery> {

    // 查询所有 => 来自继承

    // 分页查询
    PageList<Department> getAllByPage(DepartmentQuery departmentQuery);

    // 根据主键删除数据
    void delete(Long id);

    // 新增和修改的联合方法
    void saveOrUpdate(Department department);

    // 批量删除
    void delBatch(BaseQuery baseQuery);

    // 新增表单里上级部门树
    List<Department> treeDept();

    // 高级查询父级部门列表
    List<Department> getParentDept();
}
