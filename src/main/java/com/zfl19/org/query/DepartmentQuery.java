package com.zfl19.org.query;

import com.zfl19.basic.query.BaseQuery;
import lombok.Data;

@Data
public class DepartmentQuery extends BaseQuery {

    // 高级查询name
    private String name;

    // 高级查询项目经理
    private Long managerId;

    // 高级查询父级部门
    private Long parentId;

}
