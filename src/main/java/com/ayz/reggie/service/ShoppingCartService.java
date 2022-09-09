package com.ayz.reggie.service;

import com.ayz.reggie.pojo.ShoppingCart;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ShoppingCartService extends IService<ShoppingCart> {

    public boolean deleteByUserId(Long userId);
}
