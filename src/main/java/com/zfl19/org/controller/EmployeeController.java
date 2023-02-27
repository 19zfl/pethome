package com.zfl19.org.controller;

import com.zfl19.basic.query.BaseQuery;
import com.zfl19.basic.query.PageList;
import com.zfl19.basic.utils.AjaxResult;
import com.zfl19.org.domain.Employee;
import com.zfl19.org.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工的控制层
 */
@Api(value = "DepartmentController",tags = {"员工管理"})
@RestController     // @Controller + @ResponseBody联合注解
@RequestMapping("/emp")    // 暴露一级路径
public class EmployeeController {

    @Autowired
    private IEmployeeService iEmployeeService;

    /**
     * 查询所有
     * @return
     */
    @ApiOperation(value = "getAll",notes = "获取所有员工数据")
    @GetMapping("/getAll")
    public AjaxResult getAll() {
        List<Employee> list = iEmployeeService.getAll();
        return AjaxResult.createSuccess(list);
    }

    /**
     * 分页查询
     * @param baseQuery
     * @return
     */
    @ApiOperation(value = "获取员工分页数据",notes = "返回所有的分页数据")
    @PostMapping("/findPage")
    public AjaxResult findPage(@ApiParam(value = "分页参数", required = true) @RequestBody BaseQuery baseQuery) {
        PageList<Employee> listByPage = iEmployeeService.getAllByPage(baseQuery);
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
            iEmployeeService.delete(id);
            return AjaxResult.createSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.createError("删除失败!");
        }
    }

    /**
     * 新增和修改的操作
     * @param employee
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public AjaxResult saveOrUpdate(@RequestBody Employee employee) {
        try {
            iEmployeeService.saveOrUpdate(employee);
            return AjaxResult.createSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.createError("操作失败!");
        }
    }

    @GetMapping("/getAllManager")
    public AjaxResult getAllManager(){
        return AjaxResult.createSuccess(iEmployeeService.getAllManager());
    }

}
