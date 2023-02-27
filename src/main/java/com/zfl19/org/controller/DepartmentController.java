package com.zfl19.org.controller;

import com.zfl19.basic.query.BaseQuery;
import com.zfl19.basic.query.PageList;
import com.zfl19.basic.utils.AjaxResult;
import com.zfl19.org.domain.Department;
import com.zfl19.org.query.DepartmentQuery;
import com.zfl19.org.service.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return AjaxResult.createSuccess(list);
    }

    /**
     * 分页查询
     * @param departmentQuery
     * @return
     */
    @ApiOperation(value = "获取部门分页数据",notes = "返回所有的分页数据")
    @PostMapping("/findPage")
    public AjaxResult findPage(@ApiParam(value = "分页参数", required = true) @RequestBody DepartmentQuery departmentQuery) {
        PageList<Department> listByPage = iDepartmentService.getAllByPage(departmentQuery);
        return AjaxResult.createSuccess(listByPage);
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
            return AjaxResult.createSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.createError("删除失败!");
        }
    }

    /**
     * 新增和修改的操作
     * @param department
     * @return
     * @Valid 表示对当前的形参进行规则校验
     */
    @PostMapping("/saveOrUpdate")
    public AjaxResult saveOrUpdate(@Valid @RequestBody Department department) {
        try {
            iDepartmentService.saveOrUpdate(department);
            return AjaxResult.createSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.createError("操作失败!");
        }
    }

    @PostMapping("/delBatch")
    public AjaxResult delBatch(@RequestBody BaseQuery baseQuery) {
        try {
            iDepartmentService.delBatch(baseQuery);
            return AjaxResult.createSuccess("批量删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.createError("批量删除失败！");
        }
    }

    @GetMapping("/treeDept")
    public AjaxResult treeDept() {
        return AjaxResult.createSuccess(iDepartmentService.treeDept());
    }

    @GetMapping("/getParentDept")
    public AjaxResult getParentDept() {
        return AjaxResult.createSuccess(iDepartmentService.getParentDept());
    }

}
