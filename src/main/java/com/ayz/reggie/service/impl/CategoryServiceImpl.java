package com.ayz.reggie.service.impl;

import com.ayz.reggie.mapper.CategoryMapper;
import com.ayz.reggie.pojo.Category;
import com.ayz.reggie.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
