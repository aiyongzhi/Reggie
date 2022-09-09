package com.ayz.reggie.controller;

import com.ayz.reggie.common.R;
import com.ayz.reggie.common.UserIdContextHolder;
import com.ayz.reggie.pojo.Employee;
import com.ayz.reggie.service.EmployeeService;
import com.ayz.reggie.utils.DateUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javafx.scene.control.Pagination;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
@Slf4j
@RestController
public class MemberController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping(value = "/backend/page/member/addEmployee.do")
    public R<String> addEmployee(@RequestBody Employee employee, HttpSession session){
        employee.setId(null);
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        boolean save = employeeService.save(employee);
        if(save){
            return R.success("添加员工成功!");
        }else{
            return R.error("添加员工失败");
        }
    }

    /*
    * 进行带条件的分页查询
    * */

    @GetMapping(value = "/backend/page/member/queryEmployeeByNameForPage.do")
    public R<Page<Employee>> queryEmployeeByNameForPage(int page, int pageSize, String name){
        log.info("page: {},pageSize: {},name: {}",page,pageSize,name);
        //分页条件构造器
        Page<Employee> pageInfo=new Page<>(page,pageSize);
        //查询条件构造器
        LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasLength(name),Employee::getName,name);
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    /*
    * 根据员工的id修改员工的信息，可以写成一个通用的方法
    * 启用和禁用员工以及其它修改需求都可以满足
    * */
    @RequestMapping(value = "/backend/page/member/updateEmployeeById.do")
    public R<String> updateEmployeeById(HttpSession session,@RequestBody Employee employee){
        //2:调用service进行修改工作
        boolean update = employeeService.updateById(employee);
        if(update){
            return R.success("修改员工信息成功!");
        }else{
            return R.error("修改员工信息失败!");
        }
    }

    /*
    * 根据员工的id查询员工的信息
    * */
    @GetMapping(value = "/backend/page/member/queryEmployeeById.do")
    public R<Employee> queryEmployeeById(Long id){
        log.info(String.valueOf(id));
        Employee employee = employeeService.getById(id);
        if(employee!=null){
            return R.success(employee);
        }else{
            return R.error("显示员工信息失败!");
        }
    }
}
