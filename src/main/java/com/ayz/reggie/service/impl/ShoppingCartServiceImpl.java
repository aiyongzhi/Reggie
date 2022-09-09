package com.ayz.reggie.service.impl;

import com.ayz.reggie.mapper.ShoppingCartMapper;
import com.ayz.reggie.pojo.ShoppingCart;
import com.ayz.reggie.service.ShoppingCartService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
    @Override
    @Transactional
    public boolean deleteByUserId(Long userId) {
        LambdaQueryWrapper<ShoppingCart> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,userId);
        return this.remove(queryWrapper);
    }
}
