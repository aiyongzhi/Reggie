package com.ayz.reggie.service;

import com.ayz.reggie.pojo.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrderService extends IService<Orders> {

    //提交订单方法
    public void submitOrder(Orders orders);
}
