package com.zfl19.org.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_department")
public class Department {

    // 部门id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //  新增数据之后自动返回当前新增数据的id
    private Long id;
    // 部门编号
    @Size(min = 3, max = 6, message = "不合适!")       // 长度校验
    private String sn;
    // 部门名称
    @NotBlank(message = "不能输入空的!")      // 判空校验
    private String name;
    // 部门状态:1启用,0:禁用
    private Integer state = 1;
    // 部门经理id
    private Long managerId;
    // 上级部门id
    private Long parentId;
    // 部门路径
    private String dirPath;

    @Transient  // 表示该属性并非一个到数据库表的字段的映射
    // 部门经理
    private String managerName;
    @Transient  // 表示该属性并非一个到数据库表的字段的映射
    // 上级部门
    private String parentName;

    // 部门的数据结构
    @Transient
    private Long[] parentIds;
    // 部门数集合
    @Transient
    @JsonInclude(JsonInclude.Include.NON_EMPTY)     // 如果字段是空的,将会被自动剔除,避免不必要的空白数据
    private List<Department> children = new ArrayList<>();

}
