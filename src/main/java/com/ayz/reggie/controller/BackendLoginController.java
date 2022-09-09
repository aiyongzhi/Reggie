package com.ayz.reggie.controller;

import com.ayz.reggie.common.R;
import com.ayz.reggie.pojo.Employee;
import com.ayz.reggie.service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

@RestController
public class BackendLoginController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/backend/page/login/login.do",method = RequestMethod.POST)
    public R<Employee> loginController(@RequestBody Employee employee,HttpSession session){
        String username=employee.getUsername();
        String password=employee.getPassword();
        //1:将password进行加密处理
        password= DigestUtils.md5DigestAsHex(password.getBytes());
        //2:根据用户名利用索引向后台进行查询处理
        LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,username);
        Employee e = employeeService.getOne(queryWrapper);
        //3:进行验证处理
        //员工查不到  密码不匹配 处于禁用状态
        if(e==null||!password.equals(e.getPassword())){
            return R.error("用户名或密码不正确!");
        }
        if(e.getStatus()==0){
            return R.error("账号被禁用!");
        }
        //4:登录成功记录session
        session.setAttribute("employee",e.getId());
        return R.success(e);
    }
}
