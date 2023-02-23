package com.zfl19.org.controller;

import com.zfl19.basic.query.BaseQuery;
import com.zfl19.basic.query.PageList;
import com.zfl19.basic.utils.AjaxResult;
import com.zfl19.org.domain.Department;
import com.zfl19.org.service.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门的控制层
 */
@Api(value = "DepartmentController",tags = {"部门管理"})
@RestController     // @Controller + @ResponseBody联合注解
@RequestMapping("/dept")    // 暴露一级路径
public class DepartmentController {

    @Autowired
    private IDepartmentService iDepartmentService;

    /**
     * 查询所有
     * @return
     */
    @ApiOperation(value = "getAll",notes = "获取所有部门数据")
    @GetMapping("/getAll")
    public AjaxResult getAll() {
        List<Department> list = iDepartmentService.getAll();
        return AjaxResult.createSucces(list);
    }

    /**
     * 分页查询
     * @param baseQuery
     * @return
     */
    @ApiOperation(value = "获取部门分页数据",notes = "返回所有的分页数据")
    @PostMapping("/findPage")
    public AjaxResult findPage(@ApiParam(value = "分页参数", required = true) @RequestBody BaseQuery baseQuery) {
        PageList<Department> listByPage = iDepartmentService.getAllByPage(baseQuery);
        return AjaxResult.createSucces(listByPage);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable("id") Long id) {
        try {
            iDepartmentService.delete(id);
            return AjaxResult.createSucces();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.createError("删除失败!");
        }
    }

    /**
     * 新增和修改的操作
     * @param department
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public AjaxResult saveOrUpdate(@RequestBody Department department) {
        try {
            iDepartmentService.saveOrUpdate(department);
            return AjaxResult.createSucces();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.createError("操作失败!");
        }
    }

}
