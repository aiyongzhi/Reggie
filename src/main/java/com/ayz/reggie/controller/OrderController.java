package com.ayz.reggie.controller;

import com.ayz.reggie.common.R;
import com.ayz.reggie.pojo.Orders;
import com.ayz.reggie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;
    //提交订单
    @RequestMapping(value = "/front/page/order/submitOrder.do")
    public R<String> submitOrder(@RequestBody Orders orders){
        orderService.submitOrder(orders);
        return R.success("提交订单成功!");
    }
}
