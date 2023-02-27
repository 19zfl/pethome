package com.zfl19.org.mapper;

import com.zfl19.org.domain.Department;
import com.zfl19.org.query.DepartmentQuery;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 持久层
 */
public interface DepartmentMapper extends Mapper<Department> {

    /**
     * 连表查询部门数据
     * @return
     */
    List<Department> getAllDept(DepartmentQuery departmentQuery);

    /**
     * 高级查询父级部门列表
     * @return
     */
    List<Department> getParentDept();

    /**
     *
     * @param id
     * @return
     */
    List<Department> selectByParentId(Long id);

}
