package com.ayz.reggie.service.impl;

import com.ayz.reggie.DTO.DishDTO;
import com.ayz.reggie.mapper.DishMapper;
import com.ayz.reggie.pojo.Dish;
import com.ayz.reggie.pojo.DishFlavor;
import com.ayz.reggie.service.DishFlavorService;
import com.ayz.reggie.service.DishService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    DishFlavorService dishFlavorService;
    @Override
    @Transactional
    public DishDTO queryDishWithFlavorById(String id) {
        //1: 根据id查询菜品的信息
        DishDTO dishDTO=new DishDTO();
        Dish dish = this.getById(id);
        BeanUtils.copyProperties(dish,dishDTO);
        //2: 根据菜品的id查询菜品对应的口味
        LambdaQueryWrapper<DishFlavor> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,id);
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        dishDTO.setFlavors(flavors);
        return dishDTO;
    }
}
