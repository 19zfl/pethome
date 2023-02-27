package com.zfl19.org.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zfl19.basic.query.BaseQuery;
import com.zfl19.basic.query.PageList;
import com.zfl19.org.domain.Department;
import com.zfl19.org.mapper.DepartmentMapper;
import com.zfl19.org.query.DepartmentQuery;
import com.zfl19.org.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public PageList<Department> getAllByPage(DepartmentQuery departmentQuery) {
        // 设置分页参数
        PageHelper.startPage(departmentQuery.getPageNum(), departmentQuery.getPageSize());
        // 执行查询操作
        List<Department> departments = departmentMapper.getAllDept(departmentQuery);
        // 封装分页数据
        PageInfo<Department> departmentPageInfo = new PageInfo<>(departments);
        // 使用自己的工具类返回数据
        PageList<Department> departmentPageList = new PageList<>(departmentPageInfo.getTotal(), departmentPageInfo.getList());
        // 返回数据
        return departmentPageList;
    }

    @Override
    public void delete(Long id) {
        Department department1 = departmentMapper.selectByPrimaryKey(id);
        // 查询所有parentId等于参数id的数据
        List<Department> departments = departmentMapper.selectByParentId(id);
        if (department1.getParentId() == null) {
            // 表示此部门是顶级部门,直接删除
            departmentMapper.deleteByPrimaryKey(id);
        }
        // 判断根据参数id当做parentId查询的数据是否为空
        if (departments != null && departments.size() > 0) {
            // 遍历集合
            for (Department department : departments) {
                // 删除parentId为参数id的数据
                departmentMapper.deleteByPrimaryKey(department.getId());
            }
            // 先查询剩余所有的数据得到他们的dirpath
            List<Department> depts = departmentMapper.selectAll();
            // 遍历集合
            for (Department dept : depts) {
                // 得到他们的dirpath
                String dirPath = dept.getDirPath();
                // 判断dirpath中是否包含参数id
                if (dirPath.contains(String.valueOf(id))) {
                    // 有就就进行删除
                    departmentMapper.deleteByPrimaryKey(dept.getId());
                }
            }
        } else {
            departmentMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void saveOrUpdate(Department department) {
        // 如果有id就是修改
        if (department.getId() == null) {
            departmentMapper.insertSelective(department);
            // 获取上级部门层级数据
            Long[] parentIds = department.getParentIds();
            // 判空
            if (parentIds != null && parentIds.length > 0) {
                // 创建一个字符串操作对象
                StringBuilder sb = new StringBuilder();
                // 遍历parentIds
                for (int i = 0; i < parentIds.length; i++) {
                    sb.append("/" + parentIds[i]);
                }
                // 将当前主键id给到dirpath最后
                sb.append("/" + department.getId());
                // 给department进行赋值
                // 设置dirpath
                department.setDirPath(sb.toString());
                // 设置parentid
                department.setParentId(parentIds[parentIds.length - 1]);
                // 执行修改操作
                departmentMapper.updateByPrimaryKeySelective(department);
            } else {
                Long id = department.getId();
                String path = String.valueOf(id);
                department.setDirPath("/"+path);
                departmentMapper.updateByPrimaryKeySelective(department);
            }
        } else {
            // 获取上级部门层级数据
            Long[] parentIds = department.getParentIds();
            // 判空
            if (parentIds != null && parentIds.length > 0) {
                // 创建一个字符串操作对象
                StringBuilder sb = new StringBuilder();
                // 遍历parentIds
                for (int i = 0; i < parentIds.length; i++) {
                    sb.append("/" + parentIds[i]);
                }
                // 将当前主键id给到dirpath最后
                sb.append("/" + department.getId());
                // 给department进行赋值
                // 设置dirpath
                department.setDirPath(sb.toString());
                // 设置parentid
                department.setParentId(parentIds[parentIds.length - 1]);
                // 执行修改操作
                departmentMapper.updateByPrimaryKeySelective(department);
            }
        }
    }


    @Override
    public void delBatch(BaseQuery baseQuery) {
        // 获取前端传递的id数组
        Long[] ids = baseQuery.getIds();
        // 遍历数组进行删除
        for (int i = 0; i < ids.length; i++) {
            // 查询此id是否在数据存在
            Department department = departmentMapper.selectByPrimaryKey(ids[i]);
            if (department != null && !"".equals(department)) {
                delete(ids[i]);
            }
        }

    }

    @Override
    public List<Department> treeDept() {
        // 1.拿到所有的部门数据
        List<Department> departments = departmentMapper.selectAll();
        // 2.创建一个空集合
        List<Department> treeList = new ArrayList<>();
        // 3. 创建一个map,将所有的部门数据存放在里面
        Map<Long, Department> map = new HashMap<>();
        // 4. 遍历数组将数据存进map中
        for (Department dept : departments) {
            map.put(dept.getId(), dept);
            // 此时map中已经有了所有的部门数据
        }
        // 5.遍历集合
        for (Department dept : departments) {
            // 6.判断是否有上级部门id
            if (dept.getParentId() == null) {
                // 表示该部门是最顶级没有上级部门
                // 直接进行存进treeList中
                treeList.add(dept);
            } else {
                // 表示该部门有上级部门
                // 根据当前对象的上级部门id得到上级部门对象
                Department department = map.get(dept.getParentId());
                // 将当前对象存放到上级部门的children中
                department.getChildren().add(dept);

            }
        }

        return treeList;
    }

    @Override
    public List<Department> getParentDept() {
        return departmentMapper.getParentDept();
    }

}
