package com.ayz.reggie.service.impl;

import com.ayz.reggie.mapper.OrderDetailMapper;
import com.ayz.reggie.pojo.OrderDetail;
import com.ayz.reggie.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
