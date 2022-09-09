package com.ayz.reggie.service.impl;

import com.ayz.reggie.mapper.EmployeeMapper;
import com.ayz.reggie.pojo.Employee;
import com.ayz.reggie.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
