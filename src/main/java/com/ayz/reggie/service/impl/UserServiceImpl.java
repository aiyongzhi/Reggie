package com.ayz.reggie.service.impl;

import com.ayz.reggie.mapper.UserMapper;
import com.ayz.reggie.pojo.User;
import com.ayz.reggie.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
