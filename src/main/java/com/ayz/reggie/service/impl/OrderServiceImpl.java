package com.ayz.reggie.service.impl;

import com.ayz.reggie.common.UserIdContextHolder;
import com.ayz.reggie.mapper.OrderMapper;
import com.ayz.reggie.pojo.Orders;
import com.ayz.reggie.pojo.OrderDetail;
import com.ayz.reggie.pojo.ShoppingCart;
import com.ayz.reggie.service.*;
import com.ayz.reggie.utils.DateUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {

    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    UserService userService;

    @Autowired
    AddressBookService addressBookService;
    @Autowired
    ShoppingCartService shoppingCartService;
    @Override
    @Transactional
    public void submitOrder(Orders orders) {
        //1: 装备好order的各项参数
        orders.setUserId(UserIdContextHolder.getContextHolder());
        orders.setStatus(2);
        orders.setOrderTime(DateUtils.formatDateTime(new Date()));
        orders.setCheckoutTime(DateUtils.formatDateTime(new Date()));
        orders.setPhone(userService.getById(UserIdContextHolder.getContextHolder()).getPhone());
        orders.setAddress(addressBookService.getById(orders.getAddressBookId()).getDetail());

        //2:获取当前订单的详情并插入订单详情表
        LambdaQueryWrapper<ShoppingCart> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,UserIdContextHolder.getContextHolder());
        List<ShoppingCart> shoppingCartList = shoppingCartService.list(queryWrapper);
        List<OrderDetail> orderDetailList=new ArrayList<>();
        double count=0;
        for (ShoppingCart shoppingCart : shoppingCartList) {
            OrderDetail orderDetail=new OrderDetail();
            BeanUtils.copyProperties(shoppingCart,orderDetail,"id");
            count+= shoppingCart.getNumber()* shoppingCart.getAmount().doubleValue();
            orderDetailList.add(orderDetail);
        }
        orders.setAmount(BigDecimal.valueOf(count));
        this.save(orders);
        for (OrderDetail orderDetail : orderDetailList) {
            orderDetail.setOrderId(orders.getId());
        }
        orderDetailService.saveBatch(orderDetailList);
        //3:提交订单后，要清空购物车
        shoppingCartService.deleteByUserId(UserIdContextHolder.getContextHolder());
    }
}
